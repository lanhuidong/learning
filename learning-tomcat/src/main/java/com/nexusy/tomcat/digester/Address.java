package com.nexusy.tomcat.digester;

/**
 * @author lanhuidong
 * @since 2016-02-28
 */
public class Address {

    private String streetName;
    private String streeNumber;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreeNumber() {
        return streeNumber;
    }

    public void setStreeNumber(String streeNumber) {
        this.streeNumber = streeNumber;
    }

    @Override
    public String toString() {
        return "streetNumber: " + streeNumber + ", streetName: " + streetName;
    }
}
