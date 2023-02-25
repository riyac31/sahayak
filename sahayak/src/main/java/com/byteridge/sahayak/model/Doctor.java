package com.byteridge.sahayak.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;
@Repository
@Document(value = "doctor")
public class Doctor {

    @Id
    private String id;


    @JsonProperty("first_name")
    @Field(value = "first_name")
    private String first_name;


    @JsonProperty("last_name")
    @Field(value = "last_name")
    private String last_name;

    @NotBlank
    @JsonProperty("full_name")
    @Field(value = "full_name")
    private String full_name;

    @NotBlank
    @JsonProperty("specialization")
    @Field(value = "specialization")
    private String specialization;

    @JsonProperty("experience")
    @Field(value = "experience")
    private String experience;


    @NotBlank
    @JsonProperty("education")
    @Field(value = "education")
    private String education;


    @JsonProperty("average_consultation_time")
    @Field(value = "average_consultation_time")
    private int averageConsultationTime;

    @NotBlank
    @JsonProperty("price")
    @Field(value = "price")
    private String price;
    @Field("hospital_id")
    @JsonProperty("hospital_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private ObjectId hospital_id;
    @Field(value = "consultation_time")
    @JsonProperty("consultation_time")
    private int consultationTime;

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

    public String getHospital_id() {
        return hospital_id.toString();
    }

    public void setHospital_id(ObjectId hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getAverageConsultationTime() {
        return averageConsultationTime;
    }

    public void setAverageConsultationTime(int averageConsultationTime) {
        this.averageConsultationTime = averageConsultationTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public int getConsultationTime() {
        return consultationTime;
    }

    public void setConsultationTime(int consultationTime) {
        this.consultationTime = consultationTime;
    }


}
