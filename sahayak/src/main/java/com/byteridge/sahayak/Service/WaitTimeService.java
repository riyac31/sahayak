package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.model.Appointment;
import com.byteridge.sahayak.repository.AppointmentRepository;
import com.byteridge.sahayak.repository.WaitTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class WaitTimeService {

    @Autowired
    private WaitTimeRepository waitTimeRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;



    public int calculateWaitTime(String doctorId) {
        // Get all appointments for the specified doctor that are scheduled in the future
        List<Appointment> futureAppointments = appointmentRepository
                .findByDoctorIdAndAppointmentStartTimeAfter(doctorId, LocalDateTime.now());

        // Calculate the total consultation time for all future appointments
//        int totalConsultationTime = futureAppointments.stream()
//                .mapToInt(Appointment::getConsultationTime)
//                .sum();

//        // Calculate the wait time based on the average consultation time and total consultation time
//        int waitTime = Math.max(0, totalConsultationTime - averageConsultationTime * futureAppointments.size());
//
//        // Save the wait time to the database
//        WaitTime waitTimeEntity = new WaitTime();
//        waitTimeEntity.setDoctorId(doctorId);
//        waitTimeEntity.setWaitTime(waitTime);
//        waitTimeRepository.save(waitTimeEntity);

        return 1;
    }

    public long alreadyBookedAppointment(String slotStartTimeString,String slotEndTimeString,String date,String doctor_id)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("createdAt").lt(new Date()));
        query.addCriteria(Criteria.where("appointmentStartTime").is(slotStartTimeString));
        query.addCriteria(Criteria.where("appointmentEndTime").is(slotEndTimeString));
        query.addCriteria(Criteria.where("appointmentDate").is(date));
        query.addCriteria(Criteria.where("doctorId").is(doctor_id));

        long numberOfAlreadyBookedAppointments = mongoTemplate.find(query, Appointment.class).size();

        return numberOfAlreadyBookedAppointments;

    }

}
