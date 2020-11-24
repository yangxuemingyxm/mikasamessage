package com.xingzi.mikasamessage.entity;

import java.io.Serializable;
import java.util.Date;

public class Praise implements Serializable {
    private int praiseId;
    private String userNum;
    private int sayId;
    private Date praiseDate;

    public int getPraiseId() {
        return praiseId;
    }

    public void setPraiseId(int praiseId) {
        this.praiseId = praiseId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public int getSayId() {
        return sayId;
    }

    public void setSayId(int sayId) {
        this.sayId = sayId;
    }

    public Date getPraiseDate() {
        return praiseDate;
    }

    public void setPraiseDate(Date praiseDate) {
        this.praiseDate = praiseDate;
    }
}
