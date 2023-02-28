package com.byteridge.sahayak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

         @JsonProperty("first_name")
//        @Field(value = "first_name")
        private String firstName;


         @JsonProperty("last_name")
//        @Field(value = "last_name")
         private String lastName;

         @JsonProperty("patient_name")
//        @Field(value = "patient_name")
         private String patientName;

         @JsonProperty("disease")
//        @Field(value = "disease")
        private String disease;

        @JsonProperty("age")
//        @Field(value = "age")
        private String age;



         @JsonProperty("email")
//        @Field(value = "email")
        private String email;

        @NonNull
        @JsonProperty("phone_no")
//        @Field(value = "phone_no")
        private String phoneNo;

         @JsonProperty("password")
//        @Field(value = "password")
        private String password;
//        public void setEncryptedPassword(String password) {
                // Hash the password using bcrypt
//                this.password = new BCryptPasswordEncoder().encode(password);
//        }
        private String cityId;
        private String stateId;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getPatientName() {
                return patientName;
        }

        public void setPatientName(String patientName) {
                this.patientName = patientName;
        }

        public String getDisease() {
                return disease;
        }

        public void setDisease(String disease) {
                this.disease = disease;
        }

        public String getAge() {
                return age;
        }

        public void setAge(String age) {
                this.age = age;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoneNo() {
                return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
                this.phoneNo = phoneNo;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getCityId() {
                return cityId;
        }

        public void setCityId(String cityId) {
                this.cityId = cityId;
        }

        public String getStateId() {
                return stateId;
        }

        public void setStateId(String stateId) {
                this.stateId = stateId;
        }
}
