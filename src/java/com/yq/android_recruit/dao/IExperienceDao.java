package com.yq.android_recruit.dao;

import com.yq.android_recruit.pojo.Experience;

import java.util.List;

public interface IExperienceDao {
    List<Experience> getExperiences(String userId, String resumeName);
    //App删除
    void deleteExperience(String userId,String resumeName);
    //App编辑设置信息
    void updateResumeName(String userId,String oldResumeName,String newResumeName);
    //App编辑Experience
    void updateExperience(String userId,String resumeName,String expId,String yearStart,String yearEnd,String companyName,String job,String jobDescribe);
    //App创建Experience
    void createExperience(String userId,String resumeName,String yearStart,String yearEnd,String companyName,String job,String jobDescribe);
}
