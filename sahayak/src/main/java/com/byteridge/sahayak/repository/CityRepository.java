package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.City;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CityRepository extends MongoRepository<City,String> {

    @Query("{name:?0}")
    List<City> findByCityName(String cityName);

    @Query("{state_id:?0}")
    List<City> findByStateId(ObjectId stateId);

}
