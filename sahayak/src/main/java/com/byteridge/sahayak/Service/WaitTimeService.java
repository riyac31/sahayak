package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.model.Appointment;
import com.byteridge.sahayak.repository.AppointmentRepository;
import com.byteridge.sahayak.repository.WaitTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WaitTimeService {

    @Autowired
    private WaitTimeRepository waitTimeRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;



    public int calculateWaitTime(String doctorId) {
        // Get all appointments for the specified doctor that are scheduled in the future
        List<Appointment> futureAppointments = appointmentRepository
                .findByDoctorIdAndAppointmentStartTimeAfter(doctorId, LocalDateTime.now());

        // Calculate the total consultation time for all future appointments
        int totalConsultationTime = futureAppointments.stream()
                .mapToInt(Appointment::getConsultationTime)
                .sum();

//        // Calculate the wait time based on the average consultation time and total consultation time
//        int waitTime = Math.max(0, totalConsultationTime - averageConsultationTime * futureAppointments.size());
//
//        // Save the wait time to the database
//        WaitTime waitTimeEntity = new WaitTime();
//        waitTimeEntity.setDoctorId(doctorId);
//        waitTimeEntity.setWaitTime(waitTime);
//        waitTimeRepository.save(waitTimeEntity);

        return totalConsultationTime;

    }
}
