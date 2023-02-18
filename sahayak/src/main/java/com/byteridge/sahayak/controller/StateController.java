package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.helper.ValidationException;
import com.byteridge.sahayak.model.Response;
import com.byteridge.sahayak.model.State;
import com.byteridge.sahayak.repository.StateRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StateController extends ValidationException {

    private static final Logger logger = LoggerFactory.getLogger(StateController.class);
    @Autowired
    StateRepository stateRepository;

    @PostMapping("/state")
    ResponseEntity<Response> addState(@Valid @RequestBody State state)
    {
        try {
            List<State> isAlreadyExist = stateRepository.findByStateName(state.getStateName());
//            System.out.println(isAlreadyExist.size());
//            return ResponseEntity.ok(new Response(true,"",null));
            if(isAlreadyExist.isEmpty())
            {
                stateRepository.save(state);
                return ResponseEntity.ok(new Response(true,"State Created Successfully",null));
            }
            else {
                return ResponseEntity.ok(new Response(true,"State Already Exist",null));

            }

        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new Response(false,e.getMessage(),null));

        }



    }

    @GetMapping("/state")
    ResponseEntity<Response> getAllStates()
    {
        try {
            logger.info("State Fetched Successfully");
            List<State> states = stateRepository.findAll();
            return ResponseEntity.ok(new Response(true,states,null));
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return new ResponseEntity(new Response(false,e.getMessage(),null), HttpStatusCode.valueOf(500));

        }
    }



}
