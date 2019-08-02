package com.yq.android_recruit.pojo;

import com.yq.android_recruit.util.CustomDateEdtor;

import java.io.Serializable;
import java.util.Date;

public class EduJobCondition implements Serializable {
    private String eduJobConditionId,userId,resumeName,conDescribe;
    private Date times;
    CustomDateEdtor customDateEdtor = new CustomDateEdtor();
    public String getEduJobConditionId() {
        return eduJobConditionId;
    }

    public void setEduJobConditionId(String eduJobConditionId) {
        this.eduJobConditionId = eduJobConditionId;
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

    public String getTimes() {
        String convert = customDateEdtor.convert(times);
        return convert;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getConDescribe() {
        return conDescribe;
    }

    public void setConDescribe(String conDescribe) {
        this.conDescribe = conDescribe;
    }
}
