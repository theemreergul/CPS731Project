package com.example.cps731project;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    @NonNull
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String profileImg;
    private String userType;
    public User(@NonNull String username, String firstName, String lastName, String password, String phone, String email,String userType,String profileImg){
        this.username=username;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phone=phone;
        this.email=email;
        this.password=password;
        this.profileImg=profileImg;
        this.userType=userType;
    }
    public User getUser(){return this;}
    public String getUsername(){return this.username;}
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public String getPhone(){return this.phone;}
    public String getEmail(){return this.email;}
    public String getProfileImg(){return this.profileImg;}
    public String getUserType(){return this.userType;}
    public String getPassword(){return this.password;}
}
