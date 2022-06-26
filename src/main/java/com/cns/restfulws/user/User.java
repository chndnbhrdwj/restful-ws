package com.cns.restfulws.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonFilter("no id")
public class User {

    private Integer id;

    @Size(min = 2, message = "Name should be atleast 2 characters long")
    private String name;

    @Past(message = "date of birth should be in past")
    private Date dob;

    public User(Integer id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}

