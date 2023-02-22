package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.SchedulebyHos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SchedulebyHosRepository extends MongoRepository<SchedulebyHos,String> {
    List<SchedulebyHos> findByHospitalId(String hospitalId);
}
