package com.example.floresmurillo.jobquestorganizernavigation;

/**
 * Created by vanessaflores on 4/18/16.
 */
public class Application {

    private int id;
    private String companyName;
    private String jobId;
    private String dateApplied;
    private String jobTitle;
    private String interview;
    private String description;

    public Application() {
    }

    public Application(String companyName, String jobId, String dateApplied, String jobTitle, String interview, String description) {
        this.companyName = companyName;
        this.jobId = jobId;
        this.dateApplied = dateApplied;
        this.jobTitle = jobTitle;
        this.interview = interview;
        this.description = description;
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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", jobId='" + jobId + '\'' +
                ", dateApplied='" + dateApplied + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", interview='" + interview + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
