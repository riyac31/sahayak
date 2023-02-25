package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.model.Patient;
import com.byteridge.sahayak.model.PatientRegRequest;
import com.byteridge.sahayak.repository.PatientRepository;
import com.byteridge.sahayak.request.PatientLogInRequest;
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
    PatientRepository patientRegRepository;


    public String addPatient(PatientRegRequest request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Patient patient = patientRegRepository.findByEmail(request.getEmail());
            if (Objects.isNull(patient)) {
                Patient reg = objectMapper.convertValue(request, Patient.class);
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

    public List<Patient> getPatientsByDoctorId(String id) {
        return patientRepository.findByDoctorId(id);
    }
    public boolean authenticatePatient(PatientLogInRequest login) {
        Patient patient = patientRepository.findByPhoneNo(login.getPhoneNo());

            String hashedPassword = patient.getPassword();
            return (hashedPassword.equals(login.getPassword()));

    }


}

