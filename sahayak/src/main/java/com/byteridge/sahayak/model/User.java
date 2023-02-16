package com.byteridge.sahayak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="userData")
@Builder
//add all these to all classes

//to add data-- controller(write end points of api), services(write logic of what api does), repo(how data is stored)
// class/model

public class User {
    String name;
    String email;
    String phoneNo;
    String address;

}
