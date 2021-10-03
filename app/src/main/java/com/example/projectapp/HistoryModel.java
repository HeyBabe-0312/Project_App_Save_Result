package com.example.projectapp;

import java.text.SimpleDateFormat;

public class HistoryModel {
    int id;
    String date;
    int pass;
    int error;
    double rate;

    public HistoryModel(int id, String date, int pass, int error, double rate) {
        this.id = id;
        this.date = date;
        this.pass = pass;
        this.error = error;
        this.rate = rate;
    }

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

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getRate() {
        return rate+"%";
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
