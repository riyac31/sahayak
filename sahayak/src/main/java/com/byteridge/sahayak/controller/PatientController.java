package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.PatientService;
import com.byteridge.sahayak.model.Patient;
import com.byteridge.sahayak.model.PatientReg;
import com.byteridge.sahayak.model.PatientRegRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @GetMapping("/getAll")
    public List<Patient> getAll(){
        try{
            return patientService.findAll();
        }
        catch (Exception e){
            return Collections.EMPTY_LIST;

        }

    }
    @PostMapping("/addPatients")
    public List<Patient> addPatients(@RequestBody List<Patient> patients) {
        List<Patient> addedPatients = new ArrayList<>();
        for (Patient patient : patients) {
            addedPatients.add((Patient) patientService.addPatients(patient));
        }
        return addedPatients;
    }
    @PostMapping("/addPatient")
    public String addPatient(@RequestBody PatientRegRequest request) {

         return patientService.addPatient(request);

    }



}
