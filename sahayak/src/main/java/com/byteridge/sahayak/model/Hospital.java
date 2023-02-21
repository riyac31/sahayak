package com.byteridge.sahayak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Document(collection = "hospital")
public class Hospital {


    @BsonId
    @Id
    @BsonProperty("_id")
    private ObjectId id;
    @NotBlank
    @JsonProperty("hospital_name")
    @Field(value = "hospital_name",targetType = FieldType.STRING)
    private String hospitalName;


    @JsonProperty("description")
    @Field(value = "description",targetType = FieldType.STRING)
    private String description;

    @NotBlank
    @JsonProperty("email")
    @Email
    @Field(value = "email",targetType = FieldType.STRING)
    private String email;

    @NotBlank
    @JsonProperty("password")
    @Field(value = "password",targetType = FieldType.STRING)
    private String password;

    @Field(value = "image_id",targetType = FieldType.OBJECT_ID)
    private String image_id;

    @Valid
    @JsonProperty("address")
    @Field(value = "address")
    public Address address;

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
