package com.yq.android_recruit.dao;

import com.yq.android_recruit.pojo.EduJobCondition;

import java.util.List;

public interface IEduJobConditionDao {
    List<EduJobCondition> getEduJobConditions(String userId, String resumeName);
    //App删除
    void deleteEduJobCondition(String userId,String resumeName);
    //App编辑设置信息
    void updateResumeName(String userId,String oldResumeName,String newResumeName);
    //App编辑EduJobCondition
    void updateEduJobCondition(String userId,String resumeName,String eduJobConId,String times,String conDescribe);
    //App创建EduJobCondition
    void createEduJobCondition(String userId,String resumeName,String times,String conDescribe);
}
