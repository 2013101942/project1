package com.yq.android_recruit.pojo;

import java.io.Serializable;

//招聘信息bean
public class RecruitInfo implements Serializable {
    private String recruitId,eId,job,staffNum,salaryStart,salaryEnd,recruitInfo,experience;
    private String state;       //返回更新的结果是否成功
    public RecruitInfo() {
    }

    public RecruitInfo(String recruitId, String eId, String job, String staffNum, String salaryStart, String salaryEnd, String recruitInfo, String experience,String state) {
        this.recruitId = recruitId;
        this.eId = eId;
        this.job = job;
        this.staffNum = staffNum;
        this.salaryStart = salaryStart;
        this.salaryEnd = salaryEnd;
        this.recruitInfo = recruitInfo;
        this.experience = experience;
        this.state = state;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
