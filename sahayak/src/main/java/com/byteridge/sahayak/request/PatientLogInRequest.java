package com.byteridge.sahayak.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PatientLogInRequest {
    private String phoneNo;
    private String password;
}
