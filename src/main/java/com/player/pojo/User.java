package com.player.pojo;


import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String question;
    private String answer;
    private Date createTime;
    private Date updateTime;
    private Integer role;

    public User() {
    }

    public User(Integer id, String username, String password, String phone, String question, String answer,
                Date createTime, Date updateTime, Integer role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.question = question;
        this.answer = answer;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}