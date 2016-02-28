package com.nexusy.tomcat.digester;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lanhuidong
 * @since 2016-02-28
 */
public class Employee {

    private String firstName;
    private String lastName;
    private List<Office> offices = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void addOffice(Office office) {
        offices.add(office);
    }

    @Override
    public String toString() {
        return "My name is " + firstName + " " + lastName;
    }
}
