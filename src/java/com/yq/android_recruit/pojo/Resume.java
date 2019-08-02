package com.yq.android_recruit.pojo;

import java.io.Serializable;
import java.util.Date;

public class Resume implements Serializable {
    private String userId, resumeName,userName,gender,age,city,tel,qualifications,work_year,experienceId,educationId,jobIntentId,projectExperienceId,eduJobConditionId,state,isvisible;
    private Date birthday;

    public Resume() {
    }

    public Resume(String userId, String resumeName, String gender, String age, String city, String tel, String qualifications, String work_year, String state, String isvisible, Date birthday) {
        this.userId = userId;
        this.resumeName = resumeName;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.tel = tel;
        this.qualifications = qualifications;
        this.work_year = work_year;
        this.state = state;
        this.isvisible = isvisible;
        this.birthday = birthday;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getWork_year() {
        return work_year;
    }

    public void setWork_year(String work_year) {
        this.work_year = work_year;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(String isvisible) {
        this.isvisible = isvisible;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(String experienceId) {
        this.experienceId = experienceId;
    }

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getJobIntentId() {
        return jobIntentId;
    }

    public void setJobIntentId(String jobIntentId) {
        this.jobIntentId = jobIntentId;
    }

    public String getProjectExperienceId() {
        return projectExperienceId;
    }

    public void setProjectExperienceId(String projectExperienceId) {
        this.projectExperienceId = projectExperienceId;
    }

    public String getEduJobConditionId() {
        return eduJobConditionId;
    }

    public void setEduJobConditionId(String eduJobConditionId) {
        this.eduJobConditionId = eduJobConditionId;
    }
}
