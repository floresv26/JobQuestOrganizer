package com.example.floresmurillo.jobquestorganizernavigation;

/**
 * Created by vanessaflores on 4/17/16.
 */
public class Contact {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String linkedIn;
    private String companyName;

    // Default constructor
    public Contact() {
    }

    public Contact(String firstName, String lastName, String email, String phone, String linkedIn, String companyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.linkedIn = linkedIn;
        this.companyName = companyName;
    }

    /** ---------- Getters and Setters ---------- **/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", linkedIn='" + linkedIn + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
