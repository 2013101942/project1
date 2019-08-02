package com.yq.android_recruit.pojo;

import com.yq.android_recruit.util.CustomDateEdtor;

import java.io.Serializable;
import java.util.Date;

public class Education implements Serializable {
    private String eduId, userId, resumeName,school, major;
    private Date  yearsStart, yearsEnd;
    CustomDateEdtor customDateEdtor = new CustomDateEdtor();
    public String getEduId() {
        return eduId;
    }

    public void setEduId(String eduId) {
        this.eduId = eduId;
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

    public String  getYearsEnd() {
        System.out.println("教育");
        String convert = customDateEdtor.convert(yearsEnd);
        System.out.println("教育end"+convert);
        return convert;
    }

    public void setYearsEnd(Date yearsEnd) {
        this.yearsEnd = yearsEnd;
    }

    public String getYearsStart() {
        System.out.println("教育");
        String  convert = customDateEdtor.convert(yearsStart);
        System.out.println("教育yearsStart"+convert);
        return convert;
    }

    public void setYearsStart(Date yearsStart) {
        this.yearsStart = yearsStart;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
