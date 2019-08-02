package com.yq.android_recruit.pojo;

import java.io.Serializable;

//简略  简历信息
public class DivResume implements Serializable {
    private String userId, resumeName, job,name,gender,age,qualifications,major;

    public DivResume() {
    }

    public DivResume(String job, String name, String gender, String age, String qualifications, String major) {
        this.job = job;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.qualifications = qualifications;
        this.major = major;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
