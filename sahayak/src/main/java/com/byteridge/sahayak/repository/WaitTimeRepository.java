package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.WaitTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface WaitTimeRepository extends MongoRepository<WaitTime, String> {
    WaitTime findByDoctorId(String doctorId);
}
