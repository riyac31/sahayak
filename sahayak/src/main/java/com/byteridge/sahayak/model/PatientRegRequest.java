package com.byteridge.sahayak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientRegRequest {

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


}
