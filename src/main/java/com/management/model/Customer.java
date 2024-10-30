package com.management.model;

public class Customer implements CSVConvertible, Identifiable<String>{
    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    public Customer() {}

    public Customer(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getId() { return this.id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber;
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%s", id, name, email, phoneNumber);
    }
}
