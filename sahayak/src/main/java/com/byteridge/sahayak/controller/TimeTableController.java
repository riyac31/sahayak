package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.WaitTimeService;
import com.byteridge.sahayak.model.Appointment;
import com.byteridge.sahayak.model.Doctor;
import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.model.TimeTable;
import com.byteridge.sahayak.repository.AppointmentRepository;
import com.byteridge.sahayak.repository.DoctorsRepository;
import com.byteridge.sahayak.repository.TimeTableRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;


import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Slf4j
public class TimeTableController {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    WaitTimeService waitTimeService;

    @PostMapping("/schedule")
    ResponseEntity<Response> addSchedule(@Valid @RequestBody TimeTable timeTable)
    {
        try
        {
             timeTableRepository.save(timeTable);
             return new ResponseEntity(new Response(true,null,"Schedule Added Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedule")
    ResponseEntity<Response> getAllSchedule()
    {
        try
        {
            return new ResponseEntity(new Response(true,timeTableRepository.findAll(),"Schedule Added Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctorSchedule/{doctor_id}")
    ResponseEntity<Response> getAllSchedule(@PathVariable(value = "doctor_id") ObjectId doctor_id)
    {
        try
        {
            System.out.println(doctor_id);
            List<TimeTable> timeTableList  = timeTableRepository.findByDoctorId(doctor_id);
            Doctor doctor = doctorsRepository.findOneById(doctor_id);
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("schedule",timeTableList);
            data.put("doctor",doctor);
            log.info("Time Table For Doctor "+doctor_id+" fetched Successfully");
            return new ResponseEntity(new Response(true, data,"Schedule Added Successfully"), HttpStatus.OK);

        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctorTimeSlot")
    ResponseEntity<Response> getDoctorTimeSlots(@RequestParam(value = "doctor_id") ObjectId doctor_id,@RequestParam(value = "date") String date)
    {
        try
        {
            LocalDate localDate = LocalDate.parse(date);

            DayOfWeek day = localDate.getDayOfWeek();

            int dayOfWeek = day.getValue() -1 ;

            TimeTable doctorTimeTable = timeTableRepository.findByDoctorId(doctor_id).get(0);

            Doctor doctor = doctorsRepository.findOneById(doctor_id);

            Integer averageConsultationTime = doctor.getAverageConsultationTime();

            if(doctorTimeTable.getWeek_schedule().get(dayOfWeek).isEmpty())
            {
                return new ResponseEntity(new Response(true, null,"Doctor Not Available on this Date"),HttpStatus.CONFLICT);
            }

            String startTime = doctorTimeTable.getWeek_schedule().get(dayOfWeek).get(0);

            String endTime = doctorTimeTable.getWeek_schedule().get(dayOfWeek).get(1);

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);

            Date startTimeFormat = formatter.parse(startTime);

            Date endTimeFormat = formatter.parse(endTime);

            long difference_In_Time = endTimeFormat.getTime() - startTimeFormat.getTime();

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60));


            long numberOfSlots = difference_In_Minutes/60;

            if(difference_In_Minutes % 60!=0)
            {
                numberOfSlots++;
            }

            List<Map<String,String>> timeSlots = new ArrayList<Map<String,String>>();

            Calendar cal = Calendar.getInstance(); // creates calendar

            for(Integer i =0;i<numberOfSlots;i++)
            {
                cal.setTime(startTimeFormat);
                cal.add(Calendar.HOUR_OF_DAY, 1);
                Date slotStartTime = startTimeFormat;
                Date slotEndTime;


                if(endTimeFormat.compareTo(cal.getTime())<0)
                {
                    slotEndTime = endTimeFormat;
                }
                else
                {
                    slotEndTime = cal.getTime();
                }
                startTimeFormat = slotEndTime;
                long difference_In_Slot_Time = slotEndTime.getTime() - slotStartTime.getTime();

                Map<String,String> tmp = new HashMap<>() ;
                long difference_In_Slot_Time_Minutes
                        = (difference_In_Slot_Time
                        / (1000 * 60));
                System.out.println(difference_In_Slot_Time_Minutes);

                long numberOfAppointmentsAllowed = difference_In_Slot_Time_Minutes/averageConsultationTime + 1;

                String slotStartTimeString = slotStartTime.getHours()+ ":" + slotStartTime.getMinutes();

                String slotEndTimeString = slotEndTime.getHours()+ ":" + slotEndTime.getMinutes();

                long numberOfAlreadyBookedAppointments = waitTimeService.alreadyBookedAppointment(slotStartTimeString,slotEndTimeString,date,doctor_id.toString());

                long remainingAppointment = numberOfAppointmentsAllowed - numberOfAlreadyBookedAppointments;

                Calendar approximateTurnCalender = Calendar.getInstance();

                approximateTurnCalender.setTime(slotStartTime);

                approximateTurnCalender.add(Calendar.MINUTE , (int) (numberOfAlreadyBookedAppointments * averageConsultationTime));

                String approximateTurnTime = approximateTurnCalender.getTime().getHours() + ":" + approximateTurnCalender.getTime().getMinutes();

                tmp.put("slot_start",slotStartTimeString);

                tmp.put("slot_end",slotEndTimeString);

                tmp.put("maximum_appointment",String.valueOf(Math.max(remainingAppointment,0) ));

                tmp.put("approximate_turn_time",approximateTurnTime);

                timeSlots.add(tmp);
            }

            return new ResponseEntity(new Response(true, timeSlots,"Time Slots Fetched Successfully"), HttpStatus.OK);

        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
