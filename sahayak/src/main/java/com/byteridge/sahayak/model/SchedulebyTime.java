package com.byteridge.sahayak.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@Document(collection = "schedulebyTime")
public class SchedulebyTime {
    @Id
    private String id;
    private String hospitalId;
    private String doctorId;
    private String patient_name;
    private Date appointmentDate;

    private String appointmentTime;
}
