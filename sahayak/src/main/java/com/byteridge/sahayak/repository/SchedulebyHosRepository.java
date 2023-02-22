package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.SchedulebyHos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SchedulebyHosRepository extends MongoRepository<SchedulebyHos,String> {
    List<SchedulebyHos> findByHospitalId(String hospitalId);
    List<SchedulebyHos> findByAppointmentTime(String time);
}
