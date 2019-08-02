package com.yq.android_recruit.dao;

import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.pojo.*;

import java.util.List;

public interface IResumeDao {
    List<DivResume> getDivResumes(Page<DivResume> page);
    int getRecordTotal(Page<DivResume> page);
    List<String> getJobs();

    Resume getResume(String useId, String resumeName);
    List<DivResume> getReceivedResume(String companyId);
    //获取某人的所有简历
    List<Resume> getUserResumes(String userId);
    List<Experience> getWorkExperience(String userId,String resumeName);
    List<ProjectExprience> getProjectExperience(String userId,String resumeName);
    List<Education> getEducationExperience(String userId,String resumeName);
    List<EduJobCondition> getEducationWorkCondition(String userId,String resumeName);
    JobIntent getJobIntent(String userId,String resumeName);
    //删除简历
    void deleteResume(String userId,String resumeName);
    //编辑家里设置信息
    void updateResumeSetting(String userId,String oldResumeName,String newResumeName,String isVisible);
    //编辑简历基本信息 (名字 基本信息不能改)
    void updateResumeBaseInfo(String userId,String resumeName, String gender,String birthday,String age,String tel,String state,String city,String qualification,String workYear);
    //创建简历
    void  createResume(String userId,String resumeName,String isVisible);

    //PC查看审核通过的简历
    List<DivResume> checkSuccessResume(String companyId);
}
