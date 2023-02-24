package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.PatientService;
import com.byteridge.sahayak.model.Doctor;
import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.repository.DoctorsRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class DoctorsController {


    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    PatientService patientService;
    @PostMapping("/doctor")
    ResponseEntity<Response> addDoctor(@Valid @RequestBody Doctor doctor)
    {
        try {
            log.info("Doctor " + doctor.getFull_name() + " saved successfully");

            doctorsRepository.save(doctor);

            return new ResponseEntity(new Response(true, null, "Doctor Created Successfully"), HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(new Response(false, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctor")
    ResponseEntity<Response> getAllDoctor() {
        try {

            List<Doctor> doctors = doctorsRepository.findAll();
            log.info("Doctors Responded Successfully");

            return new ResponseEntity(new Response(true, doctors, ""), HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(new Response(false, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hospitalDoctor/{hospital_doctor}")
    ResponseEntity<Response> gethospitalDoctor(@PathVariable(value = "hospital_doctor") ObjectId hospitalId) {
        try {

            List<Doctor> doctors = doctorsRepository.getDoctorByHospitalId(hospitalId);
//            log.info("Doctors Responded Successfully");
//
//            // Get the average consultation time of doctors
//            int averageConsultationTime = 0;
//            for (Doctor doctor : doctors) {
//                averageConsultationTime += doctor.getConsultationTime();
//            }
//            averageConsultationTime /= doctors.size();
//
//            // Assign patients to each doctor based on the average consultation time
//            for (Doctor doctor : doctors) {
//                List<Patient> patients = patientService.getPatientsByDoctorId(doctor.getId());
//                int availableTime = 8 * 60; // 8 hours * 60 minutes/hour = 480 minutes
//                for (Patient patient : patients) {
//                    if (availableTime < averageConsultationTime) {
//                        // No more patients can be assigned to this doctor today
//                        break;
//                    }
//                    if (patient.getConsultationTime() <= availableTime) {
//                        // Assign the patient to the current doctor
//                        availableTime -= patient.getConsultationTime();
//                        patient.setDoctorId(doctor.getId());
//                    }
//                }
//            }

            return new ResponseEntity(new Response(true, doctors, ""), HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(new Response(false, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
