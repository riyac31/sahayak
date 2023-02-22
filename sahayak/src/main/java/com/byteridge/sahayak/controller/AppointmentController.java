package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.model.Appointment;
import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.repository.AppointmentRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class AppointmentController extends ValidationException {

    @Autowired
    private AppointmentRepository appointmentRepository;


    @PostMapping("/appointment")
    ResponseEntity<Response> addAppointment(@Valid @RequestBody Appointment appointment)
    {
        try
        {
            appointmentRepository.save(appointment);
            log.info("Appointment saved Successfully ");
            return new ResponseEntity(new Response(true,"Appointment Saved Successfully","Appointment Saved Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/hospitalAppointment/{hospital_id}")
    ResponseEntity<Response> hospitalAppointment(@PathVariable(value = "hospital_id") ObjectId hospitalId)
    {
        try
        {
            System.out.println(hospitalId.toString());
            List<Appointment> appointmentList =  appointmentRepository.getByHospitalId(hospitalId);
            log.info("Appointment Fetched Successfully");
            return new ResponseEntity(new Response(true,appointmentList,"Appointment Saved Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}
