package com.example.nlpc06.tabview.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NL PC 06 on 11/20/2017.
 */

public class Cabin extends RealmObject {
    @PrimaryKey
    private int id;
    private String cabin_num;
    private int cabin_type;
    private int utility;
    private boolean status;


    public Cabin() {
    }

    public Cabin(int id, String cabin_num, int cabin_type, int utility) {
        this.id = id;
        this.cabin_num = cabin_num;
        this.cabin_type = cabin_type;
        this.utility = utility;
    }

    public Cabin(String cabin_num, int cabin_type, int utility) {
        this.cabin_num = cabin_num;
        this.cabin_type = cabin_type;
        this.utility = utility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCabin_num() {
        return cabin_num;
    }

    public void setCabin_num(String cabin_num) {
        this.cabin_num = cabin_num;
    }

    public int getCabin_type() {
        return cabin_type;
    }

    public void setCabin_type(int cabin_type) {
        this.cabin_type = cabin_type;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
