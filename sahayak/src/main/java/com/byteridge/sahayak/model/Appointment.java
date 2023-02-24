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

    @Field(value = "appointmentTime")
    @JsonProperty( "appointmentTime")
    private String appointmentTime;
    @Field(value = "starttTime")
    @JsonProperty( "startTime")
    private String startTime;
    @Field(value = "endTime")
    @JsonProperty( "endTime")
    private String endTime;


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

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStart_time() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }



    public void setEndTime(String endtime) {
        this.endTime = endTime;
    }

    public String getEnd_time() {
        return startTime;
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
}
