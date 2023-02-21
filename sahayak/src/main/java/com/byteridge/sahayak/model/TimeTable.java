package com.byteridge.sahayak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.ArrayList;
import java.util.Map;

@Document(value = "time_table")
public class TimeTable {

    @Id
    private String id;

    @Field("doctor_id")
    @JsonProperty("doctor_id")
    private ObjectId doctor_id;

    @Field("week_schedule")
    @JsonProperty("week_schedule")
    private Map<Integer, ArrayList<String>> week_schedule;

    @Field("available_days")
    @JsonProperty("available_days")
    private ArrayList<String> availableDays;

    @Field("leave_days")
    @JsonProperty("leave_days")
    private ArrayList<String> leaveDays;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctor_id() {
        return doctor_id.toString();
    }

    public void setDoctor_id(ObjectId doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Map<Integer, ArrayList<String>> getWeek_schedule() {
        return week_schedule;
    }

    public void setWeek_schedule(Map<Integer, ArrayList<String>> week_schedule) {
        this.week_schedule = week_schedule;
    }

    public ArrayList<String> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(ArrayList<String> availableDays) {
        this.availableDays = availableDays;
    }

    public ArrayList<String> getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(ArrayList<String> leaveDays) {
        this.leaveDays = leaveDays;
    }
}
