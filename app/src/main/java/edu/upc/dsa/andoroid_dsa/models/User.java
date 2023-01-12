package edu.upc.dsa.andoroid_dsa.models;

import java.util.Random;

public class User {

    private String name;

    private String surname;

    private String birthday;

    private String email;

    private String password;

    private int coins;

    private int experience;

    private String profilePicture;


    public User(){}

    public User(String name, String surname, String birthday, String email, String password,int coins,int experience, String profilePicture){
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.coins=coins;
        //Random rnd=new Random();
        //this.experience= rnd.nextInt(100);
        this.experience=experience;
        this.profilePicture=profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    public void setExperience(int experience){
        this.experience=experience;

    }
    public int getExperience(){
        return this.experience;
    }
    public String getProfilePicture(){
        return this.profilePicture;
    }
    public void setProfilePicture(String profilePicture){
        this.profilePicture=profilePicture;
    }
}