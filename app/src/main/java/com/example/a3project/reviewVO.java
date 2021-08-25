package com.example.a3project;

public class reviewVO {
    // ValueObject 만들 때 필요한 것
    // 1. 필드(변수, 클래스변수) : VO에 저장하고 싶은 자료들
    private int taste;
    private int amount;
    private int speed;
    private String review;
    private String review_nick;


    // 2. 생성자 : 객체 생성할떄 저장 될 데이터를 절달받음

    public reviewVO(int taste, int amount, int speed, String review, String review_nick) {
        this.taste = taste;
        this.amount = amount;
        this.speed = speed;
        this.review = review;
        this.review_nick = review_nick;

    }

    public int getTaste() {
        return taste;
    }

    public void setTaste(int taste) {
        this.taste = taste;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview_nick() {
        return review_nick;
    }

    public void setReview_nick(String review_nick) {
        this.review_nick = review_nick;
    }
}




