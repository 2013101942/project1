package com.yq.android_recruit.dao;

import com.yq.android_recruit.pojo.ProjectExprience;

import java.util.List;

public interface IProjectExperienceDao {
    List<ProjectExprience> getProjectExpriences(String userId, String resumeName);
    //App删除
    void deleteProjectExperience(String userId,String resumeName);
    //App编辑设置信息
    void updateResumeName(String userId,String oldResumeName,String newResumeName);
    //App编辑projectExperience
    void updateProjectExperience(String userId,String resumeName,String proExpId,String timeStart,String timeEnd,String companyName,String projectName,String projectDescribe);
    //App创建projectExperience
    void createProjectExperience(String userId,String resumeName,String timeStart,String timeEnd,String companyName,String projectName,String projectDescribe);
}
