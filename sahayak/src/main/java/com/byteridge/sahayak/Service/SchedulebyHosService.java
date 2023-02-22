package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.model.SchedulebyHos;
import com.byteridge.sahayak.repository.SchedulebyHosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class SchedulebyHosService {
//    private final SchedulebyHosRepository repository;

//   @Autowired
//    public SchedulebyHosService(SchedulebyHosRepository repository) {
//        this.repository = repository;
//    }
@Autowired
SchedulebyHosRepository schedulebyHosRepository;
    public List<SchedulebyHos> getAppointmentsByHospitalId(String hospitalId) {
        return schedulebyHosRepository.findByHospitalId(hospitalId);

    }
    public List<SchedulebyHos> getAppointmentsByTime(String Time) {
        return schedulebyHosRepository.findByAppointmentTime(Time);

    }
}
