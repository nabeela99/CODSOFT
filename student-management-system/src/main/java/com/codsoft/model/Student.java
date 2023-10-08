package com.codsoft.model;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private int id;
    private String name;
    private String rollNo;
    private String grade;

    public Student(int id, String name, String rollNo, String grade) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    public Student(int id) {
        this.id = id;
        this.name = "";
        this.rollNo = "";
        this.grade = "";
    }

    public Student(String rollNo) {
        this.name = "";
        this.rollNo = rollNo;
        this.grade = "";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Student other)) {
            return false;
        }
        return this.id == other.id || Objects.equals(this.rollNo, other.rollNo);
    }

    @Override
    public String toString() {
        return super.toString();
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

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
