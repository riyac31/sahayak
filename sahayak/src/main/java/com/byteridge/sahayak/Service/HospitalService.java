package com.byteridge.sahayak.Service;

import com.byteridge.sahayak.model.Hospital;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HospitalService {

    public Hospital getJson(String hospital)
    {
        Hospital hospitalJson = new Hospital();
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            hospitalJson = objectMapper.readValue(hospital,Hospital.class);


        }
        catch (IOException e)
        {
            System.out.println("Error"+e.getMessage());
        }
        return hospitalJson;

    }
}
