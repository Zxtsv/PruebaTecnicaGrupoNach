package com.gruponach.pruebatecnica.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private Long gender_id;
    private Long job_id;
    private String name;
    private String last_name;
    private Date birthdate;

    public Employee(Long id, Long gender_id, Long job_id, String name, String last_name, Date birthdate) {
        this.id = id;
        this.gender_id = gender_id;
        this.job_id = job_id;
        this.name = name;
        this.last_name = last_name;
        this.birthdate = birthdate;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGender_id() {
        return gender_id;
    }

    public void setGender_id(Long gender_id) {
        this.gender_id = gender_id;
    }

    public Long getJob_id() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Employee [birthdate=" + birthdate + ", gender_id=" + gender_id + ", id=" + id + ", job_id=" + job_id
                + ", last_name=" + last_name + ", name=" + name + "]";
    }

    

}
