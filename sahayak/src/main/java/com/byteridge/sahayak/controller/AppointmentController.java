package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.WaitTimeService;
import com.byteridge.sahayak.model.Appointment;
import com.byteridge.sahayak.model.Hospital;
import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.repository.AppointmentRepository;
import com.byteridge.sahayak.repository.HospitalRepository;
import com.byteridge.sahayak.repository.WaitTimeRepository;
import com.byteridge.sahayak.request.AppointmentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/appointments")
@Slf4j
public class AppointmentController{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private WaitTimeService waitTimeService;

    @Autowired
    private HospitalRepository hospitalRepository;


    @PostMapping("/appointment")
    ResponseEntity<Response> addAppointment(@RequestBody Appointment appointmentRequest)
    {
        try
        {
            appointmentRepository.save(appointmentRequest);
            log.info("Appointment saved Successfully ");
            return new ResponseEntity(new Response(true,"Appointment Saved Successfully","Appointment Saved Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/hospitalAppointment/{hospital_id}")
    ResponseEntity<Response> hospitalAppointment(@PathVariable(value = "hospital_id") String hospitalId)
    {
        try
        {
            System.out.println(hospitalId.toString());
            List<Appointment> appointmentList =  appointmentRepository.findByHospitalIdOrderByAppointmentStartTime(hospitalId);
            log.info("Appointment Fetched Successfully");
            return new ResponseEntity(new Response(true,appointmentList,"Appointment Saved Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/hospitalAppointmentByTime/{time}")
    ResponseEntity<Response> hospitalAppointmentbyTime(@PathVariable(value = "time") String time)
    {
        try
        {
            System.out.println(time.toString());
            List<Appointment> appointmentList =  appointmentRepository.findAllByAppointmentStartTime(time);
            log.info("Appointment Fetched Successfully");
            return new ResponseEntity(new Response(true,appointmentList,"Appointment Fetched Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/past/patient/{patientId}")
    ResponseEntity<Response> appointmentByPatientId(@PathVariable(value="patientId")String patientId){
        try {
            List<Appointment> appointments = appointmentRepository.findByPatientIdAndCreatedAtBefore(patientId, new Date());

            return new ResponseEntity(new Response(true, appointments, "Appointments"), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/upcomingAppointment/{patient_id}")
    ResponseEntity<Response> upcomingAppointment(@PathVariable(value = "patient_id") String patientId)
    {
        try
        {
            System.out.println(patientId.toString());
            Appointment appointment =  waitTimeService.upcomingAppointment(patientId);
            if(appointment == null)
            {
                return  new ResponseEntity(new Response(true,null,"No Upcoming Appointment"), HttpStatus.OK);
            }
            log.info("Appointment "+ appointment.getId()+ " Fetched Successfully");
            Map<String,String> upcomingAppointment = new HashMap<>();
            upcomingAppointment.put("appointment_date",appointment.getAppointmentDate());
            upcomingAppointment.put("appointment_start_time",appointment.getAppointmentStartTime());
            upcomingAppointment.put("appointment_end_time",appointment.getAppointmentEndTime());
            upcomingAppointment.put("approximate_turn_time",appointment.getApproximateTurnTime());
            upcomingAppointment.put("doctor_name",appointment.getDoctor().getFull_name());
            upcomingAppointment.put("doctor_education",appointment.getDoctor().getEducation());

            Hospital hospital = hospitalRepository.findOneById(appointment.getHospitalId());
            upcomingAppointment.put("hospital_name",hospital.getHospitalName());
            upcomingAppointment.put("hospital_address",hospital.getAddress().getArea() + " " + hospital.getAddress().getCityName() );



            return new ResponseEntity(new Response(true,upcomingAppointment,"Appointment Fetched Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/pastAppointments/{patient_id}")
    ResponseEntity<Response> pastAppointment(@PathVariable(value = "patient_id") String patientId)
    {
        try
        {
            System.out.println(patientId.toString());
            List<Appointment> data =  appointmentRepository.findByPatientIdAndDateOfAppointmentBeforeOrderByDateOfAppointmentDesc(patientId, LocalDateTime.now());
            int appointmentSize = data.size();
            List<Map<String,String>> appointmentList = new ArrayList<Map<String,String>>();
            if(data.isEmpty() == true)
            {
                return  new ResponseEntity(new Response(true,appointmentList,"No Past Appointment"), HttpStatus.OK);
            }
            for(int i=0;i<appointmentSize;i++)
            {
                Appointment appointment = data.get(i);
                Map<String,String> pastAppointment = new HashMap<>();
                pastAppointment.put("date_of_appointment",appointment.getDateOfAppointment().toString());
                pastAppointment.put("appointment_date",appointment.getAppointmentDate());
                pastAppointment.put("appointment_start_time",appointment.getAppointmentStartTime());
                pastAppointment.put("appointment_end_time",appointment.getAppointmentEndTime());
                pastAppointment.put("approximate_turn_time",appointment.getApproximateTurnTime());
                pastAppointment.put("doctor_name",appointment.getDoctor().getFull_name());
                pastAppointment.put("doctor_education",appointment.getDoctor().getEducation());

                Hospital hospital = hospitalRepository.findOneById(appointment.getHospitalId());
                pastAppointment.put("hospital_name",hospital.getHospitalName());
                pastAppointment.put("hospital_address",hospital.getAddress().getArea() + " " + hospital.getAddress().getCityName() );
                appointmentList.add(pastAppointment);
            }


            return new ResponseEntity(new Response(true,appointmentList,"Appointment Fetched Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/futureAppointments/{patient_id}")
    ResponseEntity<Response> futureAppointment(@PathVariable(value = "patient_id") String patientId)
    {
        try
        {
            System.out.println(patientId.toString());
            List<Appointment> data =  appointmentRepository.findByPatientIdAndDateOfAppointmentAfterOrderByDateOfAppointmentAsc(patientId, LocalDateTime.now());
            int appointmentSize = data.size();
            List<Map<String,String>> appointmentList = new ArrayList<Map<String,String>>();
            if(data.isEmpty() == true)
            {
                return  new ResponseEntity(new Response(true,appointmentList,"No Past Appointment"), HttpStatus.OK);
            }
            for(int i=0;i<appointmentSize;i++)
            {
                Appointment appointment = data.get(i);
                Map<String,String> futureAppointment = new HashMap<>();
                futureAppointment.put("date_of_appointment",appointment.getDateOfAppointment().toString());
                futureAppointment.put("appointment_date",appointment.getAppointmentDate());
                futureAppointment.put("appointment_start_time",appointment.getAppointmentStartTime());
                futureAppointment.put("appointment_end_time",appointment.getAppointmentEndTime());
                futureAppointment.put("approximate_turn_time",appointment.getApproximateTurnTime());
                futureAppointment.put("doctor_name",appointment.getDoctor().getFull_name());
                futureAppointment.put("doctor_education",appointment.getDoctor().getEducation());

                Hospital hospital = hospitalRepository.findOneById(appointment.getHospitalId());
                futureAppointment.put("hospital_name",hospital.getHospitalName());
                futureAppointment.put("hospital_address",hospital.getAddress().getArea() + " " + hospital.getAddress().getCityName() );
                appointmentList.add(futureAppointment);
            }


            return new ResponseEntity(new Response(true,appointmentList,"Appointment Fetched Successfully"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(true,e.getMessage(),"Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
