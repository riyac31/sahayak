package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.PatientService;
import com.byteridge.sahayak.Service.WaitTimeService;
import com.byteridge.sahayak.model.Patient;
import com.byteridge.sahayak.model.PatientRegRequest;
import com.byteridge.sahayak.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public Patient addPatient(@RequestBody PatientRegRequest patient) {
//        bCryptPasswordEncoder.encode(patient.getPassword());
         return patientRepository.save(new ObjectMapper().convertValue(patient, Patient.class));

    }

    @Autowired
    private WaitTimeService waitTimeService;
    @GetMapping("/{patientId}")
    public ResponseEntity<?> getWaitTime(@PathVariable String patientId) {

        try {

            Patient patient = patientRepository.findById(patientId).get();
            int waitTime = waitTimeService.calculateWaitTime(patient.getDoctorId());
            return ResponseEntity.ok(waitTime);
        } catch (Exception e) {
            return ResponseEntity.ok("Patient not found");
        }


    }}
