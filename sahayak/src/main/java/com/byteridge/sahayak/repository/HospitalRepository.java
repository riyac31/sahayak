package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface HospitalRepository extends MongoRepository<Hospital,String> {


    List<Hospital> findByEmail(String email);

    @Query("{'address.city' : ?0 , 'address.state': ?1}")
    List<Hospital> findByCityAndStateName(String cityName,String stateName);

}
