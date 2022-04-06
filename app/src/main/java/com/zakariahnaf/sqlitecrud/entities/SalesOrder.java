package com.zakariahnaf.sqlitecrud.entities;

import java.io.Serializable;

public class SalesOrder implements Serializable {

    private int id;
    private String date;
    private int qty;
    private double amnt;
    private int status;

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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmnt() {
        return amnt;
    }

    public void setAmnt(double amnt) {
        this.amnt = amnt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
