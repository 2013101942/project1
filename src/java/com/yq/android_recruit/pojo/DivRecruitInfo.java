package com.yq.android_recruit.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yq.android_recruit.util.CustomDateEdtor;

import java.io.Serializable;
import java.util.Date;

public class DivRecruitInfo implements Serializable {
    private String recruitId, eId,eName,eProperty,city,job, staffNum,salaryStart,salaryEnd,recruitInfo,experience;


    private Date times;

    public DivRecruitInfo() {
    }

    public DivRecruitInfo(String eId, String eName, String eProperty, String city, String job, String salaryStart, String salaryEnd, Date times,String staffNum,String recruitInfo,String experience ) {
        this.eId = eId;
        this.eName = eName;
        this.eProperty = eProperty;
        this.city = city;
        this.job = job;
        this.salaryStart = salaryStart;
        this.salaryEnd = salaryEnd;
        this.times = times;
        this.staffNum = staffNum;

    }



    public String getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(String recruitId) {
        this.recruitId = recruitId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteProperty() {
        return eProperty;
    }

    public void seteProperty(String eProperty) {
        this.eProperty = eProperty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSalaryStart() {
        return salaryStart;
    }

    public void setSalaryStart(String salaryStart) {
        this.salaryStart = salaryStart;
    }

    public String getSalaryEnd() {
        return salaryEnd;
    }

    public void setSalaryEnd(String salaryEnd) {
        this.salaryEnd = salaryEnd;
    }

    public String getTimes() {
        CustomDateEdtor customDateEdtor = new CustomDateEdtor();
        String convert = customDateEdtor.convert(times);
        return convert;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getRecruitInfo() {
        return recruitInfo;
    }

    public void setRecruitInfo(String recruitInfo) {
        this.recruitInfo = recruitInfo;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
