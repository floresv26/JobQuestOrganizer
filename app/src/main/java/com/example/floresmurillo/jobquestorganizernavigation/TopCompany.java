package com.example.floresmurillo.jobquestorganizernavigation;

/**
 * Created by vanessaflores on 4/18/16.
 */
public class TopCompany {

    private int id;
    private String companyName;
    private int alumni;
    private int motivation;
    private int position;

    // Default constructor
    public TopCompany() {
    }

    public TopCompany(String companyName, int alumni, int motivation, int position) {
        this.companyName = companyName;
        this.alumni = alumni;
        this.motivation = motivation;
        this.position = position;
    }

    /** ---------- Getters and Setters ---------- **/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getAlumni() {
        return alumni;
    }

    public void setAlumni(int alumni) {
        this.alumni = alumni;
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "TopCompany{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", alumni='" + alumni + '\'' +
                ", motivation=" + motivation +
                ", position='" + position + '\'' +
                '}';
    }
}
