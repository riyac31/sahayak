package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRegRepository extends MongoRepository<Patient,String> {
   public Patient findByEmail(String email);



}