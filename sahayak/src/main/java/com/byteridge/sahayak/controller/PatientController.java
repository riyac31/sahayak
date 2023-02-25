package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.PatientService;
import com.byteridge.sahayak.Service.WaitTimeService;
import com.byteridge.sahayak.model.Patient;
import com.byteridge.sahayak.model.PatientRegRequest;
import com.byteridge.sahayak.repository.PatientRepository;
import com.byteridge.sahayak.request.PatientLogInRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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


    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody PatientLogInRequest login) {
        if (patientService.authenticatePatient(login)) {
            return new ResponseEntity<>("Log in successful",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid password or phone number",HttpStatus.UNAUTHORIZED);
        }
    }
}
