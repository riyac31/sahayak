package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.model.Patient;
import com.byteridge.sahayak.model.PatientReg;
import com.byteridge.sahayak.model.PatientRegRequest;
import com.byteridge.sahayak.repository.PatientRegRepository;
import com.byteridge.sahayak.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientRegRepository patientRegRepository;

    public List<Patient> addPatients(List<Patient> patients){
        return patientRepository.saveAll(patients);
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }


    public String addPatient(PatientRegRequest request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PatientReg patientReg = patientRegRepository.findByEmail(request.getEmail());
            if (Objects.isNull(patientReg)) {
                PatientReg reg = objectMapper.convertValue(request, PatientReg.class);
                patientRegRepository.save(reg);
                return "patients data saved successfully";
            } else {
                return "patients already registered";
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

    }
}

