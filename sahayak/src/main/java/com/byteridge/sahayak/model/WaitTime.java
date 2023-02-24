package com.byteridge.sahayak.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("waitTime")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class WaitTime {
    private String id;
    private String doctorId;
    private int waitTime;
    }
