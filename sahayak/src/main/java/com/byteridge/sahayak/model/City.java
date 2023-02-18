package com.byteridge.sahayak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document(collection = "city")
public class City {

    @Id
    private String id;

    @NotBlank(message = "city_name must not be empty")
    @Field("name")
    @JsonProperty("city_name")
    private String cityName;

    @DBRef(db = "state")
    @NotNull(message = "state_id must not be empty")
    @Field(value = "state_id",targetType = FieldType.OBJECT_ID)
    @JsonProperty("state_id")
    private String stateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", stateId=" + stateId +
                '}';
    }
}
