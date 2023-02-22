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

        @JsonProperty("patient_name")
        @Field(value = "patient_name")
        private String patient_name;

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
        @NotBlank
        @JsonProperty("phone_no")
        @Field(value = "phone_no")
        private String phone_no;

        @NotBlank
        @JsonProperty("password")
        @Field(value = "password")
        private String password;
        public void setPassword(String password) {
                // Hash the password using bcrypt
                this.password = new BCryptPasswordEncoder().encode(password);
        }



}
