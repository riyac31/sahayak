package com.byteridge.sahayak.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
public class Address {

    private int stateId;
    private int cityId;

    private String pincode;
    private String area;

    private String street;


    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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

    @Override
    public String toString() {
        return "Address{" +
                "stateId=" + stateId +
                ", cityId=" + cityId +
                ", pincode='" + pincode + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
