package com.example.a3project;

public class commuVO {

    private String title;
    private String restaurnat;
    private String time;
    private String max;
    private String current;
    private String writter;

    // 2. 생성자 : 객체 생성할떄 저장 될 데이터를 절달받음

    public commuVO(String title, String restaurnat, String time, String max, String current, String writter) {
        this.title = title;
        this.restaurnat = restaurnat;
        this.time = time;
        this.max = max;
        this.current = current;
        this.writter = writter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRestaurnat() {
        return restaurnat;
    }

    public void setRestaurnat(String restaurnat) {
        this.restaurnat = restaurnat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String wWitter) {
        this.writter = writter;
    }
}
