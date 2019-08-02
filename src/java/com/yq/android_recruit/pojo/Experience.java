package com.yq.android_recruit.pojo;

import com.yq.android_recruit.util.CustomDateEdtor;

import java.io.Serializable;
import java.util.Date;

public class Experience implements Serializable {
    private String expId,userId,resumeName,companyName,job,jobDescribe ;
    private Date yearsStart,yearsEnd;
    CustomDateEdtor customDateEdtor = new CustomDateEdtor();
    public String getExpId() {
        return expId;
    }

    public void setExpId(String expId) {
        this.expId = expId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobDescribe() {
        return jobDescribe;
    }

    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }

    public String getYearsStart() {
        String  convert = customDateEdtor.convert(yearsStart);
        System.out.println("convert"+convert);
        return convert;
    }

    public void setYearsStart(Date yearsStart) {
        this.yearsStart = yearsStart;
    }

    public String getYearsEnd() {
        String  convert = customDateEdtor.convert(yearsEnd);
        System.out.println("convert"+convert);
        return convert;
    }

    public void setYearsEnd(Date yearsEnd) {
        this.yearsEnd = yearsEnd;
    }


}
