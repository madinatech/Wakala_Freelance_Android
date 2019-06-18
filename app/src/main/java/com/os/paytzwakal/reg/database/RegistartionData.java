package com.os.paytzwakal.reg.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RegistartionData {

    public final String name;
    public final String email;
    public final String mobile;
    public final String bank;
    public final String bank_account_number;
    public final String benificary_name;
    public final String city;
    public final String pin;
    public final String contact_person;
    public final String contact_number;
    public final String location;
    public final String created;
    @PrimaryKey(autoGenerate = true)
    public Integer id;


    public RegistartionData(String name, String email, String mobile, String bank, String bank_account_number, String benificary_name, String city, String pin, String contact_person, String contact_number, String location, String created) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.bank = bank;
        this.bank_account_number = bank_account_number;
        this.benificary_name = benificary_name;
        this.city = city;
        this.pin = pin;
        this.contact_person = contact_person;
        this.contact_number = contact_number;
        this.location = location;
        this.created = created;
    }


}
