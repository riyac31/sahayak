package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.repository.SchedulebyTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SchedulebyTimeService {
    @Autowired
    SchedulebyTimeRepository schedulebyTimeRepository;
//    public List<SchedulebyTime> getAppointmentsByTime(String Time) {
//        return schedulebyTimeRepository.findByHospitalId(hospitalId);
//    }

}
