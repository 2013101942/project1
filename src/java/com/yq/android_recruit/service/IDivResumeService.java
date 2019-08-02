package com.yq.android_recruit.service;


import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.pojo.DetailedResume;
import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivResume;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDivResumeService {
    void divResumePage(Page<DivResume> page);
    List<DivResume> getReceivedResume(String companyId);

    //移动端获取自己的简历 (放到page里面)
    void getUserResumeList(Page<DivAppRecruitInfo> page);
    //App删除简历
    @Transactional
    void deleteResume(String userId,String resumeName);
    //编辑简历基本信息
    @Transactional
    public void updateResumeBaseInfo(String personId,String resumeName,String gender,String birthday,String age,String tel,String state,String city,String qualification,String workYear);
    //编辑简历设置信息
    @Transactional
    void updateResumeSetting(String userId,String oldResumeName,String newResumeName,String isVisible);

    //编辑工作经验
    @Transactional
    void updateExperience(String userId,String resumeName,String expId,String yearStart,String yearEnd,String companyName,String job,String jobDescribe);
    //创建工作经验
    @Transactional
    void createExperience(String userId,String resumeName,String yearStart,String yearEnd,String companyName,String job,String jobDescribe);

    //编辑求职意向
    @Transactional
    void updateJobIntent(String userId,String resumeName,String city,String job,String intentSalary,String arriveTime,String selfEvaluation, String selfTag);

    //编辑教育经历
    @Transactional
    void  updateEducation(String userId,String resumeName,String eduId,String yearStart,String yearEnd,String school,String major );
    //创建教育经历
    @Transactional
    void  createEducation(String userId,String resumeName,String yearStart,String yearEnd,String school,String major );

    //编辑项目经验
    @Transactional
    void updateProjectExperience(String userId,String resumeName,String proExpId,String timeStart,String timeEnd, String companyName,String projectName,String projectDescribe);
    //创建项目经验
    @Transactional
    void createProjectExperience(String userId,String resumeName,String timeStart,String timeEnd, String companyName,String projectName,String projectDescribe);

    //编辑学习及工作情况
    @Transactional
    void updateEduJobCondition(String userId,String resumeName,String eduJobConId, String times, String conDescribe );
    //创建学习及工作情况
    @Transactional
    void createEduJobCondition(String userId,String resumeName, String times, String conDescribe );

    //创建简历
    @Transactional
    void createResume(String userId,String resumeName,String isVisible);

    //查看审核通过的简历
    List<DivResume> checkSuccessResume(String companyId);
}
