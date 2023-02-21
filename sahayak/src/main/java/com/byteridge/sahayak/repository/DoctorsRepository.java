package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.Doctor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DoctorsRepository extends MongoRepository<Doctor,String> {

    @Query("{hospital_id:?0}")
    List<Doctor> getDoctorByHospitalId(ObjectId hospitalId);
}
