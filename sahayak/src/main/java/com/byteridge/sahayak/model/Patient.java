package com.byteridge.sahayak.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="patient")
@Builder
public class Patient {


        @Id
        private String id;

//        @JsonProperty("first_name")
//        @Field(value = "first_name")
        private String firstName;


//        @JsonProperty("last_name")
//        @Field(value = "last_name")
        private String lastName;

//        @JsonProperty("patient_name")
//        @Field(value = "patient_name")
        private String patientName;

//        @JsonProperty("disease")
//        @Field(value = "disease")
        private String disease;

//        @JsonProperty("age")
//        @Field(value = "age")
        private String age;


        @NonNull
//        @JsonProperty("email")
//        @Field(value = "email")
        private String email;

        @NonNull
//        @JsonProperty("phone_no")
//        @Field(value = "phone_no")
        private String phoneNo;

        @NonNull
//        @JsonProperty("password")
//        @Field(value = "password")
        private String password;
//        public void setEncryptedPassword(String password) {
                // Hash the password using bcrypt
//                this.password = new BCryptPasswordEncoder().encode(password);
//        }
        private String cityId;
        private String stateId;


}
