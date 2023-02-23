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

import java.util.Date;

@Document(value = "appointment")
public class Appointment {

    @BsonId
    @Id
    @BsonProperty("_id")
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

    public String getAppointment_time() {
        return appointmentTime;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointmentTime = appointmentTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
