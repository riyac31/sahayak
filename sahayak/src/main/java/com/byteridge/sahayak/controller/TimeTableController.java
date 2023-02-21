package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.model.TimeTable;
import com.byteridge.sahayak.repository.TimeTableRepository;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeTableController {

    @Autowired
    private TimeTableRepository timeTableRepository;

    private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);
    @PostMapping("/schedule")
    ResponseEntity<Response> addSchedule(@Valid @RequestBody TimeTable timeTable)
    {
        try
        {
             timeTableRepository.save(timeTable);
             return new ResponseEntity(new Response(true,null,"Schedule Added Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedule")
    ResponseEntity<Response> getAllSchedule()
    {
        try
        {
            return new ResponseEntity(new Response(true,timeTableRepository.findAll(),"Schedule Added Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctorSchedule/{doctor_id}")
    ResponseEntity<Response> getAllSchedule(@PathVariable(value = "doctor_id") ObjectId doctor_id)
    {
        try
        {
            System.out.println(doctor_id);
            List<TimeTable> timeTableList  = timeTableRepository.findByDoctorId(doctor_id);
            logger.info("Time Table For Doctor "+doctor_id+" fetched Successfully");
            return new ResponseEntity(new Response(true,timeTableList,"Schedule Added Successfully"), HttpStatus.OK);

        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
