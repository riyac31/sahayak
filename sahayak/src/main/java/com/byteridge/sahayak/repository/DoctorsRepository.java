package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorsRepository extends MongoRepository<Doctor,String> {

}
