package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.PatientService;
import com.byteridge.sahayak.model.*;
import com.byteridge.sahayak.repository.PatientRepository;
import com.byteridge.sahayak.request.PatientLogInRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/getAll")
    public List<Patient> getAll(){
        try{
            return patientRepository.findAll();
        }
        catch (Exception e){
            return Collections.EMPTY_LIST;

        }

    }
    @PostMapping("/addPatients")
    public List<Patient> addPatients(@RequestBody List<PatientRegRequest> patients) {
        List<Patient> addedPatients = new ArrayList<>();
        for (PatientRegRequest patient : patients) {
//            bCryptPasswordEncoder.encode(patient.getPassword());
            addedPatients.add(patientRepository.save(new ObjectMapper().convertValue(patient, Patient.class)));
        }
        return addedPatients;
    }
    @PostMapping("/addPatient")
    public ResponseEntity addPatient(@RequestBody PatientRegRequest patient) {
//        bCryptPasswordEncoder.encode(patient.getPassword());
        Patient patient1 = patientRepository.findByPhoneNo(patient.getPhoneNo());
        if(Objects.nonNull(patient1))
            return new ResponseEntity<>("Patient already registered", HttpStatus.BAD_REQUEST);
        patientRepository.save(new ObjectMapper().convertValue(patient, Patient.class));
        return new ResponseEntity<>("Patient registered", HttpStatus.OK);

    }

//    @Autowired
//    private WaitTimeService waitTimeService;
//    @GetMapping("/{patientId}")
//    public ResponseEntity<?> getWaitTime(@PathVariable String patientId) {
//
//        try {
//
//            Patient patient = patientRepository.findById(patientId).get();
//            int waitTime = waitTimeService.calculateWaitTime(patient.getDoctorId());
//            return ResponseEntity.ok(waitTime);
//        } catch (Exception e) {
//            return ResponseEntity.ok("Patient not found");
//        }





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
}
