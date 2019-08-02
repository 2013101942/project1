package com.yq.android_recruit.pojo;

import com.yq.android_recruit.util.CustomDateEdtor;

import java.io.Serializable;
import java.util.Date;

public class ProjectExprience implements Serializable {
    private String projectExpId,userId,resumeName,companyName,projectName,proDescribe;
    private Date timeStart,timeEnd;
    CustomDateEdtor customDateEdtor = new CustomDateEdtor();
    public String getProjectExpId() {
        return projectExpId;
    }

    public void setProjectExpId(String projectExpId) {
        this.projectExpId = projectExpId;
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

    public String getTimeStart() {

        String convert = customDateEdtor.convert(timeStart);
        return convert;

    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {

        String convert = customDateEdtor.convert(timeEnd);
        return convert;

    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProDescribe() {
        return proDescribe;
    }

    public void setProDescribe(String proDescribe) {
        this.proDescribe = proDescribe;
    }
}
