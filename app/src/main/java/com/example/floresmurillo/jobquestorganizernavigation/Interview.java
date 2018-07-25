package com.example.floresmurillo.jobquestorganizernavigation;

/**
 * Created by vanessaflores on 4/18/16.
 */
public class Interview {

    private int id;
    private String date;
    private String time;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String position;
    private String interviewer;
    private String details;
    private String companyName;

    public Interview() {
    }

    public Interview(String date, String time, String address1, String address2,
                     String city, String state, String zip, String position, String interviewer,
                     String details, String companyName) {
        this.date = date;
        this.time = time;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.position = position;
        this.interviewer = interviewer;
        this.details = details;
        this.companyName = companyName;
    }

    /** ---------- Getters and Setters ---------- **/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", position='" + position + '\'' +
                ", interviewer='" + interviewer + '\'' +
                ", details='" + details + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
