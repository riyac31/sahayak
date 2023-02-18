package com.byteridge.sahayak.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "city")
public class City {

    private String cityName;

    private int stateId;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
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
