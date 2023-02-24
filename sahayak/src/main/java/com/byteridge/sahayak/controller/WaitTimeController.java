package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.WaitTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/waitTime")
@Slf4j
public class WaitTimeController {
    @Autowired
    private WaitTimeService waitTimeService;
    @GetMapping("/{doctorId}")
    public ResponseEntity<Integer> getWaitTime(@PathVariable String doctorId) {
        int waitTime = waitTimeService.calculateWaitTime(doctorId);
        return ResponseEntity.ok(waitTime);
    }

}
