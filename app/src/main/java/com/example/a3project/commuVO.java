package com.example.a3project;

public class commuVO {
    // ValueObject 만들 때 필요한 것
    // 1. 필드(변수, 클래스변수) : VO에 저장하고 싶은 자료들
    private String title;
    private String restaurant;
    private String time;
    private String min;
    private String current;
    private String host_nick;
    private String host_location;
    private String content;

    // 2. 생성자 : 객체 생성할떄 저장 될 데이터를 절달받음

    public commuVO(String title, String restaurant, String time, String min, String current, String host_nick, String host_location, String content) {
        this.title = title;
        this.restaurant = restaurant;
        this.time = time;
        this.min = min;
        this.current = current;
        this.host_nick = host_nick;
        this.host_location = host_location;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHost_location() {
        return host_location;
    }

    public void setHost_location(String host_location) {
        this.host_location = host_location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getHost_nick() {
        return host_nick;
    }

    public void setHost_nick(String host_nick) {
        this.host_nick = host_nick;
    }
}
