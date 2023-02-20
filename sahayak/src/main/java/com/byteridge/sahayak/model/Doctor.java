package com.byteridge.sahayak.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "doctor")
public class Doctor {

    @Id
    private String id;

    @NotBlank
    @JsonProperty("first_name")
    @Field(value = "first_name")
    private String first_name;


    @JsonProperty("last_name")
    @Field(value = "last_name")
    private String last_name;

    @JsonProperty("full_name")
    @Field(value = "full_name")
    private String full_name;

    @JsonProperty("specialization")
    @Field(value = "specialization")
    private String specialization;

    @JsonProperty("experience")
    @Field(value = "experience")
    private String experience;




    @JsonProperty("education")
    @Field(value = "education")
    private String education;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
