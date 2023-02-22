package com.byteridge.sahayak.controller;
import com.byteridge.sahayak.model.Patient;
import com.byteridge.sahayak.model.PatientReg;
import com.byteridge.sahayak.repository.PatientRegRepository;
import com.byteridge.sahayak.repository.PatientRepository;
import org.apache.catalina.connector.Response;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class PatientRegController {
@Autowired
    PatientRegRepository patientRegRepository;



}
