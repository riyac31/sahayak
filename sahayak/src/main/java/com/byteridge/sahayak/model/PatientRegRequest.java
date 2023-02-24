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

    private String first_name;
    private String last_name;
    private String patient_name;
    private String disease;
    private String age;
    private String email;

    private String phone_no;
    private String password;
    private String doctorId;


}
