package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.SchedulebyTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulebyTimeRepository extends MongoRepository<SchedulebyTime,String> {

}
