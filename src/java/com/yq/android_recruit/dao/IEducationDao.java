package com.yq.android_recruit.dao;

import com.yq.android_recruit.pojo.Education;

import java.util.List;

public interface IEducationDao {
    List<Education> getEducations(String userId, String resumeName);
    //App删除
    void deleteEducation(String userId,String resumeName);
    //App编辑设置信息
    void updateResumeName(String userId,String oldResumeName,String newResumeName);
    //App编辑education
    void updateEducation(String userId,String resumeName,String eduId,String yearStart,String yearEnd,String school,String major);
    //App创建education
    void createEducation(String userId,String resumeName,String yearStart,String yearEnd,String school,String major);
}
