package com.byteridge.sahayak.repository;

import com.byteridge.sahayak.model.PatientLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatientLogRepository extends MongoRepository<PatientLog, String > {
    Optional<PatientLog> findByEmail(String email);
}
