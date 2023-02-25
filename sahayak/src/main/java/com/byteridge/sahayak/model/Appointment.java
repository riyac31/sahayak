package com.byteridge.sahayak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor


@Document(value = "appointment")
public class Appointment {

    @Id
    private String id;
    private String patientId;
    private String hospitalId;
    private String doctorId;
    private Date appointment_date;

    private String appointmentStartTime;

    private String appointmentEndTime;




//    public int getConsultationTime() {
//        int totalConsultationTime = doctor.getConsultationTime();
//
//        int totalAssignedPatients = patients != null ? patients.size() : 0;
//        int averageConsultationTime = totalAssignedPatients > 0 ? totalConsultationTime / totalAssignedPatients : 0;
//        return averageConsultationTime;
//    }


}
