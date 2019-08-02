package com.yq.android_recruit.dao;

import com.yq.android_recruit.pojo.JobIntent;

import java.util.List;

public interface IJobIntentDao {
    JobIntent getJobIntents(String userId, String resumeName);
    //App删除
    void deleteJobIntent(String userId,String resumeName);
    //App编辑设置信息
    void updateResumeName(String userId,String oldResumeName,String newResumeName);
    //App编辑JobIntent
    void updateJonIntent(String userId,String resumeName,String city,String job,String intentSalary,String arriveTime,String selfEvaluation,String selfTag);
    //App创建JobIntent
    void createJobIntent(String userId,String resumeName);

    List<String> getQualifications();
}
