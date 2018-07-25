package com.example.floresmurillo.jobquestorganizernavigation;

/**
 * Created by vanessaflores on 4/18/16.
 */
public class Reminder {

    private int id;
    private String date;
    private String time;
    private String title;
    private String notes;

    public Reminder() {
    }

    public Reminder(String date, String time, String title, String notes) {
        this.date = date;
        this.time = time;
        this.title = title;
        this.notes = notes;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
