package com.example.nlpc06.tabview.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NL PC 06 on 12/13/2017.
 */

public class Release extends RealmObject{
    @PrimaryKey
    private int id;
    private String name;
    private int age;
    private String address;
    private int cabin_id;
    private int doctor_id;
    private String contact_number;
    private String imagePath;
    private long releaseDate;


    public Release() {
    }

    public Release(int id, String name, int age, String address, int cabin_id, int doctor_id, String contact_number, String imagePath, long releaseDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.cabin_id = cabin_id;
        this.doctor_id = doctor_id;
        this.contact_number = contact_number;
        this.imagePath = imagePath;
        this.releaseDate = releaseDate;
    }

    public Release(Patient patient) {
        this.name = patient.getName();
        this.age = patient.getAge();
        this.address = patient.getAddress();
        this.cabin_id = patient.getCabin_id();
        this.doctor_id = patient.getDoctor_id();
        this.contact_number = patient.getContact_number();
        this.imagePath = patient.getImagePath();
        this.releaseDate = System.currentTimeMillis();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCabin_id() {
        return cabin_id;
    }

    public void setCabin_id(int cabin_id) {
        this.cabin_id = cabin_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }
}
