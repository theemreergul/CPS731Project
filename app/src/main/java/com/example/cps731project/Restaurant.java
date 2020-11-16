package com.example.cps731project;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant_table")
public class Restaurant {
    @PrimaryKey
    @NonNull
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String photo;
    public Restaurant(@NonNull String name, String address, String phone, String email, String website, String photo){
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.website=website;
        this.photo=photo;
        this.email=email;
    }
    public Restaurant getRestaurant(){return this;}
    public String getName(){return  this.name;}
    public String getAddress(){return  this.address;}
    public String getPhone(){return  this.phone;}
    public String getEmail(){return  this.email;}
    public String getWebsite(){return  this.website;}
    public String getPhoto(){return  this.photo;}

}
