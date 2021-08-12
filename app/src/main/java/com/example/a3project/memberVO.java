package com.example.a3project;

public class memberVO {

    private String id;
    private String pw;
    private String address;
    private int age;
    private String gender;
    private int cash;

    public memberVO(String id, String pw, String address, int age, String gender, int cash) {
        this.id = id;
        this.pw = pw;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.cash = cash;
    }

    public memberVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}

