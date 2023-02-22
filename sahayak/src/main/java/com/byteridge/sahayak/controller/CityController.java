package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.helper.ValidationException;
import com.byteridge.sahayak.model.City;
import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.repository.CityRepository;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class CityController extends ValidationException {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    CityRepository cityRepository;

    @PostMapping("/city")
    ResponseEntity<Response> addCity(@Valid @RequestBody City city)
    {
            try
            {
                    List<City> cities = cityRepository.findByCityName(city.getCityName().toLowerCase());
                    if(cities.isEmpty())
                    {
                        logger.info(city.getCityName()+ "saved Successfully");
                        city.setCityName(city.getCityName().toLowerCase());
                        cityRepository.save(city);
                        return new ResponseEntity(new Response(true, "City Saved Successfully",null), HttpStatusCode.valueOf(200));
                    }
                    else
                    {
                        return new ResponseEntity(new Response(true, "City Already Exist",null), HttpStatusCode.valueOf(200));
                    }
            }
            catch (Exception e)
            {
                        logger.error(e.getMessage());
                         return new ResponseEntity(new Response(false, e.getMessage(),null), HttpStatusCode.valueOf(500));

            }
    }

    @GetMapping("/city/{stateId}")
    ResponseEntity<Response> getCityByStateId(@PathVariable ObjectId stateId)
    {
        try {
            List<City> cities = cityRepository.findByStateId(stateId);
            logger.info("City Fetched Successfully");

            if(cities.isEmpty())
            {
                return ResponseEntity.ok(new Response(true,cities,"Cities not Found"));
            }
            else
            {
                return ResponseEntity.ok(new Response(true,cities,null));
            }

        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return new ResponseEntity(new Response(false,e.getMessage(),"Something went Wrong"),HttpStatusCode.valueOf(500));
        }


    }



}
