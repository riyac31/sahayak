package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.State;
import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StateRepository extends MongoRepository<State,String> {

    @Query("{state:?0}")
    List<State> findByStateName(String stateName);

}
