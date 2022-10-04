package com.muebles.ev1.services;

import com.muebles.ev1.entities.Empleados;
import com.muebles.ev1.entities.EmpleadosPlanilla;
import com.muebles.ev1.entities.Marcas;
import com.muebles.ev1.entities.Planillas;
import com.muebles.ev1.repositories.EmpleadosPlanillasRepository;
import com.muebles.ev1.repositories.PlanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import static java.lang.Math.max;

import static java.lang.Integer.parseInt;


@Service
public class PlanillaService {
    @Autowired
    PlanillaRepository planillaRepository;
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    EmpleadosPlanillasRepository empleadosPlanillasRepository;
    @Autowired
    MarcaService marcaService;
    public ArrayList<Planillas> getPlanillas(){
        return (ArrayList<Planillas>) planillaRepository.findAll();
    }
    public int horasDia(int dia, int mes , int anyo, String rut){
        LocalDate date = LocalDate.of(anyo,mes,dia);
        ArrayList<Marcas> marcas = marcaService.getByDayAndRut(date, rut);
        if(marcas.size() < 2)
            return 0;
        String[] hSalida = marcas.get(1).getHora().toString().split(":");
        String[] hEntrada = marcas.get(0).getHora().toString().split(":");
        int nSalida = parseInt(hSalida[0]);
        int nEntrada = parseInt(hEntrada[0]);
        if(nSalida-nEntrada > 10 && marcas.get(0).getAutorizacion()) {
            return Math.max(nSalida - nEntrada - 10, 0);
        }
        return 0;
    }
    public void delete(){planillaRepository.deleteAllPlanillas();}
    public ArrayList<Integer> getAtrasosAndInasist(int mes, int anyo, String rut){
        ArrayList<Integer> atrasosInasist = new ArrayList<>();
        atrasosInasist.add(0);
        atrasosInasist.add(0);
        atrasosInasist.add(0);
        atrasosInasist.add(0);
        LocalDate date = LocalDate.of(anyo, mes, 1);
        for (int j = 1; j < date.lengthOfMonth(); j++) {
            date = LocalDate.of(anyo, mes, j);
            ArrayList<Marcas> marcas = marcaService.getByDayAndRut(date, rut);
            System.out.println("-" +date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US)+ "-");
            if(marcas.isEmpty()) {
                if(!date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US).equals("Saturday") &&
                        !date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US).equals("Sunday"))
                {
                    atrasosInasist.set(3, atrasosInasist.get(3) + 1);
                }
            }
            else if (!date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US).equals("Saturday") &&
                    !date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US).equals("Sunday") &&
                    marcas.get(0).getHora() == null && marcas.get(1).getHora() == null &&
                    !marcas.get(0).getJustificativo())
            {
                atrasosInasist.set(3, atrasosInasist.get(3) + 1);
            }
            else if(marcas.get(0).getHora().isAfter(LocalTime.parse("09:10:00")) && !marcas.get(0).getJustificativo()){
                atrasosInasist.set(3, atrasosInasist.get(3) + 1);
            } else if (marcas.get(0).getHora().isAfter(LocalTime.parse("08:45"))) {
                atrasosInasist.set(2, atrasosInasist.get(2) + 1);
            } else if (marcas.get(0).getHora().isAfter(LocalTime.parse("08:25"))) {
                atrasosInasist.set(1, atrasosInasist.get(1) + 1);
            } else if (marcas.get(0).getHora().isAfter(LocalTime.parse("08:10"))) {
                atrasosInasist.set(0, atrasosInasist.get(0) + 1);
            }
        }
        return atrasosInasist;
    }

    public Planillas calculateSueldo(String rut, ArrayList<Integer> atIn, int horas, int anyo){
        Planillas planilla = new Planillas();
        Empleados empleado = empleadoService.findByRut(rut);

        int DESC_AT_10MIN = 1;
        int DESC_AT_25MIN = 3;
        int DESC_AT_45MIN = 6;
        int DESC_AT_70MIN = 15;
        int COT_PREV = 10;
        int COT_SAL = 8;

        int BONUS_ANYOS_0_4 = 0;
        int BONUS_ANYOS_5_9 = 5;
        int BONUS_ANYOS_10_14 = 8;
        int BONUS_ANYOS_15_19 = 11;
        int BONUS_ANYOS_20_24 = 14;
        int BONUS_ANYOS_25 = 17;
        
        planilla.setRut_empleado(empleado.getRut());
        planilla.setSfm(empleadoService.calculateSFM(empleado.getCategoria()));
        int anyosServicio = empleadoService.getAnyosServicio(rut, anyo);
        if(anyosServicio < 5) {
            planilla.setMonto_anyos(BONUS_ANYOS_0_4 * planilla.getSfm()/100);
        }
        else if (anyosServicio < 10) {
            planilla.setMonto_anyos(BONUS_ANYOS_5_9 * planilla.getSfm() / 100);
        } else if (anyosServicio < 15) {
            planilla.setMonto_anyos(BONUS_ANYOS_10_14 * planilla.getSfm() / 100);
        } else if (anyosServicio < 20) {
            planilla.setMonto_anyos(BONUS_ANYOS_15_19 * planilla.getSfm() / 100);
        } else if (anyosServicio < 25) {
            planilla.setMonto_anyos(BONUS_ANYOS_20_24 * planilla.getSfm() / 100);
        }
        else
            planilla.setMonto_anyos(BONUS_ANYOS_25 * planilla.getSfm() / 100);

        planilla.setMonto_horas(horas * empleadoService.getValueOvertime(empleado.getCategoria()));
        planilla.setMonto_desc(atIn.get(0) * DESC_AT_10MIN * planilla.getSfm()/100 +
                atIn.get(1) * DESC_AT_25MIN * planilla.getSfm()/100 +
                atIn.get(2) * DESC_AT_45MIN * planilla.getSfm()/100
                + atIn.get(3)*DESC_AT_70MIN * planilla.getSfm()/100);
        planilla.setMonto_final(max(planilla.getSfm()
                + planilla.getMonto_anyos()
                + planilla.getMonto_horas()
                - planilla.getMonto_desc(), 0));
        planilla.setCot_sal(planilla.getMonto_final() * COT_SAL / 100);
        planilla.setCot_prev(planilla.getMonto_final() * COT_PREV / 100);
        planilla.setMonto_final(planilla.getMonto_final() - planilla.getCot_prev() - planilla.getCot_sal());
        return planilla;
    }

    public String generate(int mes, int anyo){
        ArrayList<String> ruts = empleadoService.getRuts();
        LocalDate date = LocalDate.of(anyo, mes, 1);
        if(ruts.isEmpty())
            return "Error al generar la planilla";
        else{
            for (String rut : ruts) {
                int horas_ex = 0;
                for (int j = 1; j < date.lengthOfMonth(); j++) {
                    //Se recopilan todas las horas trabajadas
                    if (!date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US).equals("SATURDAY") &&
                            !date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US).equals("SUNDAY")) {
                        horas_ex = horas_ex + horasDia(j, mes, anyo, rut);
                    }
                }
                ArrayList<Integer> atrasosInasist = getAtrasosAndInasist(mes, anyo, rut);
                Planillas sueldo = calculateSueldo(rut, atrasosInasist, horas_ex, anyo);
                planillaRepository.insertPlanilla(sueldo.getRut_empleado(),sueldo.getSfm(),sueldo.getMonto_anyos(),
                        sueldo.getMonto_horas(),sueldo.getMonto_desc(),sueldo.getMonto_final(),sueldo.getCot_prev(),
                        sueldo.getCot_sal());
            }
            return "Planilla generada correctamente";
        }
    }

    public ArrayList<EmpleadosPlanilla> getEmpPlan() {
        return empleadosPlanillasRepository.getEmpPlan();
    }
}
