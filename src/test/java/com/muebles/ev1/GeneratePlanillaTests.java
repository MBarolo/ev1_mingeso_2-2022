package com.muebles.ev1;

import com.muebles.ev1.entities.Empleados;
import com.muebles.ev1.entities.Marcas;
import com.muebles.ev1.entities.Planillas;
import com.muebles.ev1.services.EmpleadoService;
import com.muebles.ev1.services.MarcaService;
import com.muebles.ev1.services.PlanillaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest
public class GeneratePlanillaTests {
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    MarcaService marcaService;
    @Autowired
    PlanillaService planillaService;
    @Test
    void calculateSueldo(){
        Empleados empleado_test = new Empleados();
        empleado_test.setRut("20.457.671-9");
        empleado_test.setApellidos("apellidos_test");
        empleado_test.setNombres("nombres_test");
        empleado_test.setCategoria("a");
        empleado_test.setFecha_ingreso(LocalDate.parse("2000-01-01"));
        empleado_test.setFecha_nac(LocalDate.parse("2000-01-01"));
        empleadoService.insertEmpleado(empleado_test);

        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.parse("2022-06-01"));
        dates.add(LocalDate.parse("2022-06-02"));
        dates.add(LocalDate.parse("2022-06-03"));
        dates.add(LocalDate.parse("2022-06-06"));
        dates.add(LocalDate.parse("2022-06-07"));
        dates.add(LocalDate.parse("2022-06-08"));
        dates.add(LocalDate.parse("2022-06-09"));
        dates.add(LocalDate.parse("2022-06-10"));
        dates.add(LocalDate.parse("2022-06-13"));
        dates.add(LocalDate.parse("2022-06-14"));
        dates.add(LocalDate.parse("2022-06-15"));
        dates.add(LocalDate.parse("2022-06-16"));
        dates.add(LocalDate.parse("2022-06-17"));
        dates.add(LocalDate.parse("2022-06-20"));
        dates.add(LocalDate.parse("2022-06-21"));
        dates.add(LocalDate.parse("2022-06-22"));
        dates.add(LocalDate.parse("2022-06-23"));
        dates.add(LocalDate.parse("2022-06-24"));
        dates.add(LocalDate.parse("2022-06-27"));
        dates.add(LocalDate.parse("2022-06-28"));
        dates.add(LocalDate.parse("2022-06-29"));
        dates.add(LocalDate.parse("2022-06-30"));

        ArrayList<LocalTime> timesIn = new ArrayList<>();
        timesIn.add(LocalTime.parse("08:00:00"));
        timesIn.add(LocalTime.parse("08:05:00"));
        timesIn.add(LocalTime.parse("07:55:00"));
        timesIn.add(LocalTime.parse("08:55:00"));
        timesIn.add(LocalTime.parse("08:20:00"));
        timesIn.add(LocalTime.parse("08:10:00"));
        timesIn.add(LocalTime.parse("08:11:00"));
        timesIn.add(LocalTime.parse("09:40:00"));
        timesIn.add(LocalTime.parse("08:11:00"));
        timesIn.add(LocalTime.parse("08:40:00"));
        timesIn.add(LocalTime.parse("08:30:00"));
        timesIn.add(LocalTime.parse("08:17:00"));
        timesIn.add(LocalTime.parse("08:16:00"));
        timesIn.add(LocalTime.parse("08:00:00"));
        timesIn.add(LocalTime.parse("08:01:00"));
        timesIn.add(LocalTime.parse("07:55:00"));
        timesIn.add(LocalTime.parse("08:05:00"));
        timesIn.add(LocalTime.parse("08:10:00"));
        timesIn.add(LocalTime.parse("08:20:00"));
        timesIn.add(LocalTime.parse("08:40:00"));
        timesIn.add(LocalTime.parse("08:00:00"));
        timesIn.add(LocalTime.parse("08:01:00"));

        ArrayList<LocalTime> timesOut = new ArrayList<>();
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:20:00"));
        timesOut.add(LocalTime.parse("18:50:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("19:00:00"));
        timesOut.add(LocalTime.parse("20:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:00:00"));
        timesOut.add(LocalTime.parse("18:20:00"));

        Marcas marca = new Marcas();
        marca.setAutorizacion(false);
        marca.setJustificativo(false);
        marca.setRut_empleado(empleado_test.getRut());
        for(int i = 0; i < dates.size(); i++){
            marca.setFecha(dates.get(i));
            marca.setHora(timesIn.get(i));
            marcaService.insertMarca(marca);
            marca.setHora(timesOut.get(i));
            marcaService.insertMarca(marca);
        }

        planillaService.generate(dates.get(0).getMonthValue(), dates.get(0).getYear());
         ArrayList<Planillas> planillas = planillaService.getPlanillas();
        int montoFinal = planillas.get(0).getMonto_final();
        int finalTeorico = 1087320;

        assertEquals(finalTeorico, montoFinal, finalTeorico*0.01);
    }
}
