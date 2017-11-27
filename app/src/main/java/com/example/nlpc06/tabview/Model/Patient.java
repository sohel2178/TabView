package com.example.nlpc06.tabview.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NL PC 06 on 11/27/2017.
 */

public class Patient extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private int age;
    private String address;
    private int cabin_id;
    private String contact_number;


    public Patient() {
    }


    public Patient(int id, String name, int age, String address, int cabin_id, String contact_number) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.cabin_id = cabin_id;
        this.contact_number = contact_number;
    }


    public Patient(String name, int age, String address, int cabin_id, String contact_number) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.cabin_id = cabin_id;
        this.contact_number = contact_number;
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

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
}
