package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.Appointment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Repository

public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId> {

    @Query("{hospital_id :?0 }")
    List<Appointment> getByHospitalId(ObjectId hospital_id);

    List<Appointment> findByDoctorIdAndAppointmentStartTimeAfter(String doctorId, LocalDateTime now);
    List<Appointment> findByDoctorIdAndAppointmentDateAfter(String doctorId, Date now);

    List<Appointment> findAllByAppointmentStartTime(String time);
    List<Appointment> findByHospitalIdOrderByAppointmentStartTime(String hospitalId);
     List <Appointment> findByPatientIdAndCreatedAtBefore(String patientId, Date Now);
     List <Appointment> findByPatientIdAndCreatedAtAfter(String patientId, LocalDateTime Now);


     List<Appointment> findByPatientIdAndDateOfAppointmentBeforeOrderByDateOfAppointment(String patientId,LocalDateTime date);

     List<Appointment> findByPatientIdAndDateOfAppointmentAfterOrderByDateOfAppointment(String patientId,LocalDateTime date);

    Appointment findByPatientId(String patientId);


}
