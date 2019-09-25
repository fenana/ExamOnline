package com.exam.model;

public class Friend {
    private Integer id;
    private String name;
    private String motto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
    public Friend() {

    }
    public Friend( String name, String motto) {
        this.name = name;
        this.motto = motto;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", motto='" + motto + '\'' +
                '}';
    }
}
