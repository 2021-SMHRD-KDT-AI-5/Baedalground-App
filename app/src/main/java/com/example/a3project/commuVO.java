package com.example.a3project;

public class commuVO {
    // ValueObject 만들 때 필요한 것
    // 1. 필드(변수, 클래스변수) : VO에 저장하고 싶은 자료들
    private String title;
    private int restaurant;
    private String time;
    private int min;
    private String host_id;
    private String content;

    // 2. 생성자 : 객체 생성할떄 저장 될 데이터를 절달받음

    public commuVO(String title, int restaurant, String time, int min, String host_id, String content) {
        this.title = title;
        this.restaurant = restaurant;
        this.time = time;
        this.min = min;
        this.host_id = host_id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String gethost_id() {
        return host_id;
    }

    public void sethost_id(String host_id) {
        this.host_id = host_id;
    }
}
