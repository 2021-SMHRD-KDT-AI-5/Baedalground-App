package com.example.a3project;

public class memberVO {

    private String id;
    private String pw;
    private String address;
    private String age;
    private String gender;

    public memberVO(String id, String pw, String address, String age, String gender) {
        this.id = id;
        this.pw = pw;
        this.address = address;
        this.age = age;
        this.gender = gender;
    }

    public memberVO(){

    };

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
