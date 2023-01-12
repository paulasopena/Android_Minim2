package edu.upc.dsa.andoroid_dsa.models;

public class UserInformation {
    String name;
    String surname;
    String birthday;
    String email;
    String password;
    int coins;
    int experience;
    String profilePicture;

    public UserInformation(){};

    public UserInformation(String name, String surname, String birthday, String email, String password,int coins, int experience, String profilePicture){
        this.setName(name);
        this.setSurname(surname);
        this.setBirthday(birthday);
        this.setEmail(email);
        this.setPassword(password);
        this.setCoins(coins);
        this.setExperience(experience);
        this.setProfilePicture(profilePicture);

    }



    public UserInformation(User u){
        this.setName(u.getName());
        this.setSurname(u.getSurname());
        this.setBirthday(u.getBirthday());
        this.setEmail(u.getEmail());
        this.setPassword(u.getPassword());
        this.setCoins(u.getCoins());
        this.setExperience(u.getExperience());
        this.setProfilePicture(u.getProfilePicture());
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    public void setExperience(int experience) {
        this.experience=experience;
    }
    public int getExperience(){
        return this.experience;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture=profilePicture;
    }
    public String getProfilePicture(){
        return this.profilePicture;
    }
}