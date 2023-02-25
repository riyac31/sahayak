package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.model.Appointment;
import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.repository.AppointmentRepository;
import com.byteridge.sahayak.request.AppointmentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Slf4j
public class AppointmentController{

    @Autowired
    private AppointmentRepository appointmentRepository;


    @PostMapping("/appointment")
    ResponseEntity<Response> addAppointment(@RequestBody AppointmentRequest appointmentRequest)
    {
        try
        {
            appointmentRepository.save(new ObjectMapper().convertValue(appointmentRequest, Appointment.class));
            log.info("Appointment saved Successfully ");
            return new ResponseEntity(new Response(true,"Appointment Saved Successfully","Appointment Saved Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/hospitalAppointment/{hospital_id}")
    ResponseEntity<Response> hospitalAppointment(@PathVariable(value = "hospital_id") String hospitalId)
    {
        try
        {
            System.out.println(hospitalId.toString());
            List<Appointment> appointmentList =  appointmentRepository.findByHospitalIdOrderByAppointmentStartTime(hospitalId);
            log.info("Appointment Fetched Successfully");
            return new ResponseEntity(new Response(true,appointmentList,"Appointment Saved Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/hospitalAppointmentByTime/{time}")
    ResponseEntity<Response> hospitalAppointmentbyTime(@PathVariable(value = "time") String time)
    {
        try
        {
            System.out.println(time.toString());
            List<Appointment> appointmentList =  appointmentRepository.findAllByAppointmentStartTime(time);
            log.info("Appointment Fetched Successfully");
            return new ResponseEntity(new Response(true,appointmentList,"Appointment Fetched Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

//    @GetMapping("/hospitalAppointment/{time}")
//    ResponseEntity<Response> hospitalAppointmentbyTime(@PathVariable(value = "time") String time)
//    {
//        try
//        {
//            System.out.println(time.toString());
//            List<Appointment> appointmentList =  appointmentRepository.findAllByAppointmentTime(time);
//            log.info("Appointment Fetched Successfully");
//            return new ResponseEntity(new Response(true,appointmentList,"Appointment Fetched Successfully"), HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
//
//        }
//    }
}
