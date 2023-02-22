package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.PatientReg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRegRepository extends MongoRepository<PatientReg,String> {
   public PatientReg findByEmail(String email);


}