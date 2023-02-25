package com.byteridge.sahayak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document(value = "appointment")
public class Appointment {

    private final ArrayList<Object> patients;
    @BsonId
    @Id
    @BsonProperty("id")
    private ObjectId id;

    @BsonRepresentation(BsonType.OBJECT_ID)
    @Field(value = "patient_id")
    @JsonProperty("patient_id")
    private ObjectId patientId;

    @BsonRepresentation(BsonType.OBJECT_ID)
    @Field(value = "hospital_id")
    @JsonProperty("hospital_id")
    private ObjectId hospitalId;

    @BsonRepresentation(BsonType.OBJECT_ID)
    @Field(value = "doctor_id")
    @JsonProperty("doctor_id")
    private ObjectId doctorId;

    @Field(value = "appointment_date")
    @JsonProperty( "appointment_date")
    private Date appointment_date;


    @Field(value = "appointment_start_time")
    @JsonProperty( "appointment_start_time")
    private String appointmentStartTime;


    @Field(value = "appointment_end_time")
    @JsonProperty( "appointment_end_time")
    private String appointmentEndTime;


    @Field(value = "doctor")
    @JsonProperty("doctor")
    @Valid
    Doctor doctor;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getHospitalId() {
        return hospitalId.toString();
    }

    public void setHospitalId(ObjectId hospitalId) {
        this.hospitalId = hospitalId;
    }

    public ObjectId getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(ObjectId doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
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
    public Appointment() {
        this.patients = new ArrayList<>();
    }
    public void assignPatient(Patient patient) {
        this.patients.add(patient);
    }
    public int getConsultationTime() {
        int totalConsultationTime = doctor.getConsultationTime();

        int totalAssignedPatients = patients != null ? patients.size() : 0;
        int averageConsultationTime = totalAssignedPatients > 0 ? totalConsultationTime / totalAssignedPatients : 0;
        return averageConsultationTime;
    }

    public ObjectId getPatientId() {
        return patientId;
    }

    public void setPatientId(ObjectId patientId) {
        this.patientId = patientId;
    }
}
