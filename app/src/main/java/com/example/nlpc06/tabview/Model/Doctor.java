package com.example.nlpc06.tabview.Model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NL PC 06 on 12/6/2017.
 */

public class Doctor extends RealmObject implements Serializable {
    @PrimaryKey
    private int id;

    private String name;
    private String degree;
    private String speciality;
    private String contact;
    private String imagePath;

    public Doctor() {
    }

    public Doctor(int id, String name, String degree, String speciality, String contact, String imagePath) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.speciality = speciality;
        this.contact = contact;
        this.imagePath = imagePath;
    }

    public Doctor(String name, String degree, String speciality, String contact, String imagePath) {
        this.name = name;
        this.degree = degree;
        this.speciality = speciality;
        this.contact = contact;
        this.imagePath = imagePath;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
