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
        private String firstName;
        private String lastName;
        private String patientName;
        private String disease;
        private String age;
        private String email;

        private String phoneNo;
        private String password;
        private String cityId;
        private String stateId;

        private  String notificationToken;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getNotificationToken() {
                return notificationToken;
        }

        public void setNotificationToken(String notificationToken) {
                this.notificationToken = notificationToken;
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
