package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.Appointment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository

public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId> {

    @Query("{hospital_id :?0 }")
    List<Appointment> getByHospitalId(ObjectId hospital_id);

    List<Appointment> findByDoctorIdAndStartTimeAfter(String doctorId, LocalDateTime now);

    List<Appointment> findAllByAppointmentTime(String time);
    List<Appointment> findByHospitalIdOrderByAppointmentTime(String hospitalId);

}
