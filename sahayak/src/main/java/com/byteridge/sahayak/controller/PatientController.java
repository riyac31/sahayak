package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.PatientService;
import com.byteridge.sahayak.model.*;
import com.byteridge.sahayak.repository.PatientRepository;
import com.byteridge.sahayak.request.PatientLogInRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/addPatient")
    public ResponseEntity addPatient(@RequestBody Patient patient) {
//        bCryptPasswordEncoder.encode(patient.getPassword());
        try {
            Patient patient1 = patientRepository.findByPhoneNo(patient.getPhoneNo());
            if(patient1!=null)
                return new ResponseEntity<Response>(new Response(true,null,"Patient already registered"), HttpStatus.OK);
            patientRepository.save(patient);
            return new ResponseEntity<Response>(new Response(true,null,"Patient registered Successfully"), HttpStatus.OK);

        }
        catch (Exception e)
        {
            return  new ResponseEntity<Response>(new Response(false,null,"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);


        }


    }






    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PatientLogInRequest login) {
        Patient patient = patientService.authenticatePatient(login);
        if (Objects.nonNull(patient)) {
            return new ResponseEntity<>(patient,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Incorrect Password or Phone Number",HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/waitTime/{patientId}")
    public ResponseEntity<?> getWaitTime(@PathVariable(value = "patientId") String patientId){

        try {
            int waitingTime = patientService.getWaitingTime(patientId);

            return new ResponseEntity(new Response(true, waitingTime, "Waiting Time"), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/storeToken/{patient_id}")
    public ResponseEntity<?> storeToken(@PathVariable(value = "patientId") String patientId,@RequestBody String token){

        try {
            Patient patient = patientRepository.findOneById(patientId);
            patient.setNotificationToken(token);
            mongoTemplate.save(patient);
            return new ResponseEntity(new Response(true, "Token Saved", "Token Saved"), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
