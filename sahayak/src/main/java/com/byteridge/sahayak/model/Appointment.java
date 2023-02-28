package com.byteridge.sahayak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor


@Document(value = "appointment")
public class Appointment {

    @Id
    private String id;

    @JsonProperty("patient_id")
    private String patientId;

    @JsonProperty("hospital_id")
    private String hospitalId;

    @JsonProperty("doctor_id")
    private String doctorId;
    @JsonProperty("appointment_date")
    private String appointmentDate ;

    @JsonProperty("appointment_start_time")
    private String appointmentStartTime;

    @JsonProperty("appointment_end_time")
    private String appointmentEndTime;

    @JsonProperty("doctor")
    private Doctor doctor;

    @JsonProperty("patient")
    private Patient patient;

    private LocalDateTime createdAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public void setAppointmentStartTime(String appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    public String getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void setAppointmentEndTime(String appointmentEndTime) {
        this.appointmentEndTime = appointmentEndTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    //    public int getConsultationTime() {
//        int totalConsultationTime = doctor.getConsultationTime();
//
//        int totalAssignedPatients = patients != null ? patients.size() : 0;
//        int averageConsultationTime = totalAssignedPatients > 0 ? totalConsultationTime / totalAssignedPatients : 0;
//        return averageConsultationTime;
//    }


}
