package com.byteridge.sahayak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "address")
public class Address {


    @JsonProperty("state_name")
    @NotBlank
    @Field(value = "state")
    private String stateName;
    @JsonProperty("city_name")
    @NotBlank
    @Field(value = "city")
    private String cityName;

    @JsonProperty("pincode")
    @NotBlank
    @Field(value = "pincode")

    private String pincode;
    @JsonProperty("area")
    @NotBlank
    @Field(value = "area")

    private String area;

    @JsonProperty("street")
    @Field(value = "street")

    private String street;

    public String getStateName() {
        return stateName.toLowerCase();
    }

    public void setStateName(String stateName) {
        this.stateName = stateName.toLowerCase();
    }

    public String getCityName() {
        return cityName.toLowerCase();
    }

    public void setCityName(String cityName) {
        this.cityName = cityName.toLowerCase();
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
