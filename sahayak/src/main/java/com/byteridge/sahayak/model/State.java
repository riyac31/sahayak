package com.byteridge.sahayak.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "state")
public class State {

    @Id
    private String id;
    @Field("state")
    @NotBlank(message = "State Name is Mandatory")
    private String stateName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "{" +
                "name :'" + stateName + '\'' +
                '}';
    }
}
