package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.model.BCryptPasswordEncoder;
import com.byteridge.sahayak.model.PatientLog;
import com.byteridge.sahayak.repository.PatientLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientLogService {
    private final PatientLogRepository patientLogRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public PatientLogService(PatientLogRepository patientLogRepository, BCryptPasswordEncoder passwordEncoder) {
        this.patientLogRepository = patientLogRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public boolean authenticatePatient(PatientLog login) {
        Optional<PatientLog> patientEntityOptional = patientLogRepository.findByEmail(login.getEmail());

        if (patientEntityOptional.isPresent()) {
            PatientLog patientLog = patientEntityOptional.get();
            String hashedPassword = patientLog.getPassword();

            return passwordEncoder.matches(login.getPassword(), hashedPassword);
        } else {
            return false;
        }
    }
    public boolean matches(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}
