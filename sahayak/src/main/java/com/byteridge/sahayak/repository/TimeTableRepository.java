package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.TimeTable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TimeTableRepository extends MongoRepository<TimeTable,String> {

     @Query("{doctor_id:?0}")
     List<TimeTable> findByDoctorId(ObjectId doctorId);

}
