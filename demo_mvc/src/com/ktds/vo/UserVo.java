package com.ktds.vo;

import java.util.Date;

public class UserVo {

    private int id;
    private String userid;
    private String name;
    private String gender;
    private String city;
    private Date regdate;

    public UserVo() {

    }

    public UserVo(int id, String userid, String name, String gender, String city, Date regdate) {

        this.id = id;
        this.userid = userid;
        this.name = name;
        this.gender = gender;
        this.city = city;
        this.regdate = regdate;
    }

    public UserVo(String userid, String name, String gender, String city) {
        this.userid = userid;
        this.name = name;
        this.gender = gender;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "UserVO [id=" + id + ", userid=" + userid + ", name=" + name + ", gender=" + gender + ", city=" + city
                + ", regdate=" + regdate + "]";
    }

}