package com.byteridge.sahayak.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequest {

    private String patientId;
    private String hospitalId;
    private String doctorId;


    private String appointmentStartTime;

    private String appointmentEndTime;

}
