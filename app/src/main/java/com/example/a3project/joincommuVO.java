package com.example.a3project;

public class joincommuVO {
    // ValueObject 만들 때 필요한 것
    // 1. 필드(변수, 클래스변수) : VO에 저장하고 싶은 자료들
    private String title;
    private String restaurant;
    private String time;
    private String member;
    private String host_location;
    private String host_nick;
    private String order_menu;
    private String order_price;
    private String my_nick;



    // 2. 생성자 : 객체 생성할떄 저장 될 데이터를 절달받음

    public joincommuVO(String title, String restaurnat, String time, String member, String host_location,  String host_nick, String order_menu, String order_price, String my_nick ) {
        this.title = title;
        this.restaurant = restaurnat;
        this.time = time;
        this.host_nick = host_nick;
        this.host_location = host_location;
        this.member = member;
        this.order_menu = order_menu;
        this.order_price = order_price;
        this.my_nick = my_nick;
    }

    public String getHost_location() {
        return host_location;
    }

    public void setHost_location(String host_location) {
        this.host_location = host_location;
    }

    public String getHost_nick() {
        return host_nick;
    }

    public void setHost_nick(String host_nick) {
        this.host_nick = host_nick;
    }

    public String getMy_nick() {
        return my_nick;
    }

    public void setMy_nick(String my_nick) {
        this.my_nick = my_nick;
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

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getOrder_menu() {
        return order_menu;
    }

    public void setOrder_menu(String order_menu) {
        this.order_menu = order_menu;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }
}




