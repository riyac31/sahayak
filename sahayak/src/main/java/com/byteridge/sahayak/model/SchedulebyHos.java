package com.byteridge.sahayak.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class SchedulebyHos {
    @Id
    private String id;
    private String hospitalId;
    private String doctorId;
    private String patient_name;
    private Date appointmentDate;
}
