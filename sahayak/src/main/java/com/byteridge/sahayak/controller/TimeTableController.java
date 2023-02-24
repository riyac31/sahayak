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
import java.util.*;

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

//            System.out.println(doctor_id);
//            System.out.println(date);

            LocalDate localDate = LocalDate.parse(date);

            //    System.out.println(localDate);

            DayOfWeek day = localDate.getDayOfWeek();

            int dayOfWeek = day.getValue() -1 ;
//            System.out.println(day);
//            System.out.println(dayOfWeek);
            TimeTable doctorTimeTable = timeTableRepository.findByDoctorId(doctor_id).get(0);

            String startTime = doctorTimeTable.getWeek_schedule().get(dayOfWeek).get(0);

            String endTime = doctorTimeTable.getWeek_schedule().get(dayOfWeek).get(1);

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);

            Date startTimeFormat = formatter.parse(startTime);

            Date endTimeFormat = formatter.parse(endTime);

            //String formattedDateString = formatter.format(date);
//            System.out.println(startTime);
//            System.out.println(endTime);
//            System.out.println(startTimeFormat.getTime());
//            System.out.println(endTimeFormat.getTime());
//            System.out.println(startTimeFormat);
//            System.out.println(endTimeFormat);

            long difference_In_Time = endTimeFormat.getTime() - startTimeFormat.getTime();

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60));

            //System.out.println(difference_In_Minutes);

            long numberOfSlots = difference_In_Minutes/60;

            if(difference_In_Minutes % 60!=0)
            {
                numberOfSlots++;
            }

            List<List<String>> timeSlots = new ArrayList<List<String>>();

            Calendar cal = Calendar.getInstance(); // creates calendar

            for(Integer i =0;i<numberOfSlots;i++)
            {
                cal.setTime(startTimeFormat);
                cal.add(Calendar.HOUR_OF_DAY, 1);
                Date slotStartTime = startTimeFormat;
                Date slotEndTime;
                System.out.println(cal.getTime());
                if(endTimeFormat.compareTo(cal.getTime())<0)
                {
                    slotEndTime = endTimeFormat;
                    System.out.println(slotEndTime);
                }
                else
                {
                    slotEndTime = cal.getTime();
                }
                startTimeFormat = slotEndTime;
                List<String> tmp = new ArrayList<String>();
                tmp.add(slotStartTime.getHours()+ ":" + slotStartTime.getMinutes());
                tmp.add(slotEndTime.getHours()+ ":" + slotEndTime.getMinutes());
                timeSlots.add(tmp);
            }

            return new ResponseEntity(new Response(true,timeSlots,"Schedule Added Successfully"), HttpStatus.OK);

        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
