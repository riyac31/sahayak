package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends MongoRepository<Patient,String> {
    public Patient findByEmail(String email);
    public Patient findByPhoneNo(String phone_no);



    List<Patient> findByDoctorId(String DoctorId);
}
