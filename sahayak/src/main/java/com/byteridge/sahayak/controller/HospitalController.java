package com.byteridge.sahayak.controller;


import com.byteridge.sahayak.Service.FileService;
import com.byteridge.sahayak.Service.HospitalService;
import com.byteridge.sahayak.helper.ValidationException;
import com.byteridge.sahayak.model.Hospital;
import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.repository.HospitalRepository;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/hospital")
public class HospitalController  extends ValidationException {

    @Autowired
    private FileService fileService;
    @Autowired
    private HospitalService hospitalService;
    private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);


    @Autowired
    private HospitalRepository hospitalRepository;
    @PostMapping(value = "/hospital",consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    ResponseEntity<Response> addHospital(@RequestParam(value = "file",required = false) MultipartFile file,@Valid @RequestPart("hospital") String hospital)
    {
        try
        {
            Hospital hospitalObject = hospitalService.getJson(hospital);
            List<Hospital> hospitalList = hospitalRepository.findByEmail(hospitalObject.getEmail());

            if(hospitalList.isEmpty())
            {
                String imageId = fileService.addFile(file);
                hospitalObject.setImage_id(imageId);
                hospitalRepository.save(hospitalObject);
                logger.info("Hospital" + hospitalObject.getHospitalName() +"created successfully");
                return ResponseEntity.ok(new Response(true,null,"Hospital created Successfully"));

            }
            else
            {
                return ResponseEntity.ok(new Response(true,null,"Hospital Already Exists"));
            }

        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new Response(true,e.getMessage(),null));

        }

    }

    @GetMapping("/cityHospital")
    ResponseEntity<Response> getHospitalByCityName(@RequestParam(value = "city_id") ObjectId cityId)
    {
          try
          {

              List<Hospital> hospitalList = hospitalRepository.findByCityId(cityId);
              return new ResponseEntity(new Response(true,hospitalList,"Hospital Fetched Successfully"),HttpStatus.OK);
          }
          catch (Exception e)
          {
              return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"), HttpStatus.OK);
          }
    }

    @GetMapping("/hospitalLogin")
    ResponseEntity<Response> hospitalLogin(@RequestParam(value = "email") String email,@RequestParam(value = "password") String password)
    {
        try
        {
            List<Hospital> hospitalList = hospitalRepository.findByEmail(email);
            if(hospitalList.isEmpty())
            {
                return new ResponseEntity(new Response(true,"Hosital Not Registered","Hospital Not Registered"),HttpStatus.OK);
            }
            else
            {
                if(hospitalList.get(0).getPassword().equals(password) )
                {
                    return new ResponseEntity(new Response(true,hospitalList.get(0),"Logged In Successfully"),HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity(new Response(true,null,"Incorrect Password"),HttpStatus.OK);
                }
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Response(false,e.getMessage(),"Something Went Wrong"), HttpStatus.OK);
        }
    }

}
