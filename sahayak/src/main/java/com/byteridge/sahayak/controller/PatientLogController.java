package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.PatientLogService;
import com.byteridge.sahayak.model.PatientLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class PatientLogController {
    private final PatientLogService patientLogService;

    public PatientLogController(PatientLogService patientLogService) {
        this.patientLogService = patientLogService;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody PatientLog login) {
        if (patientLogService.authenticatePatient(login)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
