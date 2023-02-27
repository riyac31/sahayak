package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.Hospital;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface HospitalRepository extends MongoRepository<Hospital,String> {


    List<Hospital> findByEmail(String email);

    @Query("{'city_id' : ?0}")
    List<Hospital> findByCityId(ObjectId cityName);

}
