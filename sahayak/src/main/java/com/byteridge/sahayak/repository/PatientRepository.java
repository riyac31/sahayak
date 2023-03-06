package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.Patient;
import com.byteridge.sahayak.model.PatientRegRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient,String> {
//    public Patient findByEmail(String email);
    public Patient findByPhoneNo(String phone_no);

    public Patient findOneById(String id);


//    List<Patient> findByDoctorId(String DoctorId);
}
