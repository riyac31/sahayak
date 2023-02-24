package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.model.TimeTable;
import com.byteridge.sahayak.repository.TimeTableRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
public class TimeTableController {

    @Autowired
    private TimeTableRepository timeTableRepository;

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
            log.info("Time Table For Doctor "+doctor_id+" fetched Successfully");
            return new ResponseEntity(new Response(true,timeTableList,"Schedule Added Successfully"), HttpStatus.OK);

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

            System.out.println(doctor_id);
            System.out.println(date);
            LocalDate localDate = LocalDate.parse(date);
            System.out.println(localDate);
            DayOfWeek day = localDate.getDayOfWeek();
            int dayOfWeek = day.getValue() -1 ;
            System.out.println(day);
            System.out.println(dayOfWeek);
            TimeTable doctorTimeTable = timeTableRepository.findByDoctorId(doctor_id).get(0);
            String startTime = doctorTimeTable.getWeek_schedule().get(dayOfWeek).get(0);
            String endTime = doctorTimeTable.getWeek_schedule().get(dayOfWeek).get(1);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
            Date startTimeFormat = formatter.parse(startTime);
            //String formattedDateString = formatter.format(date);
            System.out.println(startTime);
            System.out.println(endTime);

            return new ResponseEntity(new Response(true,doctorTimeTable,"Schedule Added Successfully"), HttpStatus.OK);

        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
