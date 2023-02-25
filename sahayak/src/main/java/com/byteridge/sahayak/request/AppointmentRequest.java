package com.byteridge.sahayak.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequest {

    private String patientId;
    private String hospitalId;
    private String doctorId;
    private Date appointment_date;

    private String appointmentStartTime;

    private String appointmentEndTime;

}
