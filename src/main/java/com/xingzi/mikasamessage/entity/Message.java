package com.xingzi.mikasamessage.entity;

public class Message {
    private String msg;
    private User user;
    public Message() {
        super();
    }
    public Message(User user) {
        super();
        this.user = user;
    }
    public Message(String msg) {
        super();
        this.msg = msg;
    }
    public Message(String msg, User user) {
        super();
        this.msg = msg;
        this.user = user;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}