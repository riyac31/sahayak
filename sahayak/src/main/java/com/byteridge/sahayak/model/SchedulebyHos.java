package com.byteridge.sahayak.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "schedulebyHospital")
@ToString
@Builder
public class SchedulebyHos {
    @Id
    private String id;
    private String hospitalId;
    private String doctorId;
    private String patient_name;
    private Date appointmentDate;
    private String appointmentTime;
}
