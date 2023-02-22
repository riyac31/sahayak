package com.byteridge.sahayak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="patient")
@Builder
public class PatientReg {


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

        @JsonProperty("disease")
        @Field(value = "disease")
        private String disease;

        @JsonProperty("age")
        @Field(value = "age")
        private String age;


        @NotBlank
        @JsonProperty("email")
        @Field(value = "email")
        private String email;




}