package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.model.Doctor;
import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.repository.DoctorsRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorsController {

    @Autowired
    DoctorsRepository doctorsRepository;
    private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);
    @PostMapping("/doctor")
    ResponseEntity<Response> addDoctor(@Valid @RequestBody Doctor doctor)
    {
            try{
                logger.info("Doctor "+doctor.getFull_name()+" saved Successffully");

                doctorsRepository.save(doctor);

                return new ResponseEntity(new Response(true,null,"Doctor Created Successfully"), HttpStatus.OK);

            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                return new ResponseEntity(new Response(false,null,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    @GetMapping("/doctor")
    ResponseEntity<Response> getAllDoctor()
    {
        try{

            List<Doctor> doctors =  doctorsRepository.findAll();
            logger.info("Doctors Responded Successfully");

            return new ResponseEntity(new Response(true,doctors,""), HttpStatus.OK);

        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return new ResponseEntity(new Response(false,null,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
