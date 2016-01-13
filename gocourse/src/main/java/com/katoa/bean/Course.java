package com.katoa.bean;

/**
 * Created by 徐灿 on 2015/4/9
 * Contact 384454501@qq.com
 * Introduce 课程实体：课程ID,课程名，教师姓名，课程时间，上课地点
 */
public class Course {
    //课程ID
    private int couId;

    //课程名
    private String couName;

    //课程教师姓名
    private String couTeaName;

    //课程时间
    private String couTime;

    //课程地点
    private String couLoc;

    //get and set
    public int getCouId() {
        return couId;
    }

    public void setCouId(int couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getCouTeaName() {
        return couTeaName;
    }

    public void setCouTeaName(String couTeaName) {
        this.couTeaName = couTeaName;
    }

    public String getCouTime() {
        return couTime;
    }

    public void setCouTime(String couTime) {
        this.couTime = couTime;
    }

    public String getCouLoc() {
        return couLoc;
    }

    public void setCouLoc(String couLoc) {
        this.couLoc = couLoc;
    }

    //构造函数
    public Course(){}

    public Course(int couId, String couName, String couTeaName, String couTime, String couLoc) {
        this.couId = couId;
        this.couName = couName;
        this.couTeaName = couTeaName;
        this.couTime = couTime;
        this.couLoc = couLoc;
    }
}
