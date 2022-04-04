package com.zakariahnaf.sqlitecrud.model;

public class Employees {
    int id;
    String name, department, joiningdate;
    double salary;

    public Employees(int id, String name, String department, String joiningdate, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.joiningdate = joiningdate;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
