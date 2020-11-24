package com.xingzi.mikasamessage.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private int commentId;
    private String userNum;
    private String content;
    private String image;
    private Date commentDate;
    private int sayId;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getSayId() {
        return sayId;
    }

    public void setSayId(int sayId) {
        this.sayId = sayId;
    }
}
