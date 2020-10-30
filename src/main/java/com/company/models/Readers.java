package com.company.models;

public class Readers {
    private int id;
    private String fname;
    private String lname;
    private String reg_date;

    public Readers() {
    }

    public Readers(int id, String fname, String lname, String reg_date) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.reg_date = reg_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
}

