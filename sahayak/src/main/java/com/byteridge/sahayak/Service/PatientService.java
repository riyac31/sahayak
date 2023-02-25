package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.model.Appointment;
import com.byteridge.sahayak.model.Doctor;
import com.byteridge.sahayak.model.Patient;
import com.byteridge.sahayak.repository.AppointmentRepository;
import com.byteridge.sahayak.repository.DoctorsRepository;
import com.byteridge.sahayak.repository.PatientRepository;
import com.byteridge.sahayak.request.PatientLogInRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DoctorsRepository doctorsRepository;



//    public String addPatient(PatientRegRequest request) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Patient patient = patientRegRepository.findByEmail(request.getEmail());
//            if (Objects.isNull(patient)) {
//                Patient reg = objectMapper.convertValue(request, Patient.class);
//                patientRegRepository.save(reg);
//                return "patients data saved successfully";
//            } else {
//                return "patients already registered";
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return e.getMessage();
//        }
//
//    }

//    public List<Patient> getPatientsByDoctorId(String id) {
//        return patientRepository.findByDoctorId(id);
//    }
    public Patient authenticatePatient(PatientLogInRequest login) {
        Patient patient = patientRepository.findByPhoneNo(login.getPhoneNo());

            String hashedPassword = patient.getPassword();
           if(hashedPassword.equals(login.getPassword()))
               return patient;
           else
               return null;

    }
    public int getWaitingTime(String patientId){
        try {
            Appointment appointment = appointmentRepository.findByPatientId(patientId);
            String doctorId = appointment.getDoctorId();
            Doctor doctor = doctorsRepository.findById(doctorId).get();
            int averageConsultationTime = doctor.getAverageConsultationTime();
            List<Appointment> appointments = appointmentRepository.findByPatientIdAndAppointmentDateAfter(patientId, appointment.getAppointmentDate());
            log.info("{}", appointments);
            return appointments.size() * averageConsultationTime;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }


}

