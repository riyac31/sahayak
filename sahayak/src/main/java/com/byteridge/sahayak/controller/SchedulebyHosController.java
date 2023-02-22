package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.SchedulebyHosService;
import com.byteridge.sahayak.model.SchedulebyHos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class SchedulebyHosController {
    private final SchedulebyHosService service;
    @Autowired
    public SchedulebyHosController(SchedulebyHosService service){

        this.service = service;
    }
    @GetMapping("/hospital/{hospitalId}")
    public List<SchedulebyHos> getAppointmentsByHospitalId(@PathVariable String hospitalId){
        return service.getAppointmentsByHospitalId(hospitalId);
    }
    @GetMapping("/{time}")
    public List<SchedulebyHos> getAppointmentsByTime(@PathVariable String Time){
        return service.getAppointmentsByTime(Time);
    }
}
