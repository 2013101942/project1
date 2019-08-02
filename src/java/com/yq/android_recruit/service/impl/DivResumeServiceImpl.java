package com.yq.android_recruit.service.impl;

import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.dao.*;
import com.yq.android_recruit.pojo.*;
import com.yq.android_recruit.service.IDivResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("divResume")
public class DivResumeServiceImpl implements IDivResumeService {

    @Autowired
    private IResumeDao resumeDao;
    @Autowired
    private IPersonCompanyDao personCompanyDao;
    @Autowired
    private IEducationDao educationDao;
    @Autowired
    private IEduJobConditionDao eduJobConditionDao;
    @Autowired
    private IExperienceDao experienceDao;
    @Autowired
    private IJobIntentDao jobIntentDao;
    @Autowired
    private IProjectExperienceDao projectExperienceDao;
    @Autowired
    private ICollectionDao collectionDao;


    @Override
    public void divResumePage(Page<DivResume> page) {
        page.setDivBeans(resumeDao.getDivResumes(page));

        System.out.println("获取几个数据库中简历："+page.getDivBeans().size());
        page.setRecordTotal(resumeDao.getRecordTotal(page));
        page.setJobs(resumeDao.getJobs());
        page.setQualifications(jobIntentDao.getQualifications());
    }

    //查询收到的简历
    @Override
    public List<DivResume> getReceivedResume(String companyId){
        List<DivResume> receivedResume = resumeDao.getReceivedResume(companyId);
        System.out.println("DivResume有几个"+receivedResume.size());
        return receivedResume;
    }

    //移动端获取自己的简历（在此 组装成 DetailedResume）
    @Transactional
    @Override
    public void getUserResumeList(Page<DivAppRecruitInfo> page) {
        List<DetailedResume> detailedResumeList = new ArrayList<>();
        String userId = page.getUserId();
        //获取正常简历
        List<Resume> userResumes = resumeDao.getUserResumes(userId);
        for (Resume resume:userResumes){
            DetailedResume detailedResume = new DetailedResume();
            detailedResume.setResumeBean(resume);
            //获取工作经验
            detailedResume.setExperienceList(resumeDao.getWorkExperience(userId,resume.getResumeName()));
            //获取项目经验
            detailedResume.setProjectExprienceList(resumeDao.getProjectExperience(userId,resume.getResumeName()));
            //获取教育经历
            detailedResume.setEducationList(resumeDao.getEducationExperience(userId,resume.getResumeName()));
            //获取学习或工作情况
            detailedResume.setEduJobConditionList(resumeDao.getEducationWorkCondition(userId,resume.getResumeName()));
            //获取求职意向
            detailedResume.setJobIntentBean(resumeDao.getJobIntent(userId,resume.getResumeName()));
            detailedResumeList.add(detailedResume);
        }
        page.setDetailedResumes(detailedResumeList);
    }
    //App删除简历 (删除改简历所有相关)
    @Transactional
    public void deleteResume(String userId,String resumeName){
        resumeDao.deleteResume(userId,resumeName);
        educationDao.deleteEducation(userId,resumeName);
        eduJobConditionDao.deleteEduJobCondition(userId,resumeName);
        experienceDao.deleteExperience(userId,resumeName);
        jobIntentDao.deleteJobIntent(userId,resumeName);
        projectExperienceDao.deleteProjectExperience(userId,resumeName);
        personCompanyDao.deletePersonCompanyApp(userId,resumeName);
        collectionDao.deleteCollectionApp(userId,resumeName);
    }
    //App编辑简历设置(修改简历名时要 修改所有相关表,要更改所有的表中的简历名)
    @Transactional
    public void updateResumeSetting(String userId,String oldResumeName,String newResumeName,String isVisible){
        resumeDao.updateResumeSetting(userId,oldResumeName,newResumeName,isVisible);
        educationDao.updateResumeName(userId,oldResumeName,newResumeName);
        eduJobConditionDao.updateResumeName(userId,oldResumeName,newResumeName);
        experienceDao.updateResumeName(userId,oldResumeName,newResumeName);
        jobIntentDao.updateResumeName(userId,oldResumeName,newResumeName);
        projectExperienceDao.updateResumeName(userId,oldResumeName,newResumeName);
        personCompanyDao.updateResumeName(userId,oldResumeName,newResumeName);
        collectionDao.updateResumeName(userId,oldResumeName,newResumeName);
        System.out.println("修改成功.......");
    }
    //App修改简历基本信息（名字基本信息不能改）
    @Transactional
    public void updateResumeBaseInfo(String userId,String resumeName,String gender,String birthday,String age,String tel,String state,String city,String qualification,String workYear){
        workYear = ("null".equals(workYear))? "":workYear;
        resumeDao.updateResumeBaseInfo(userId,resumeName,gender,birthday,age,tel,state,city,qualification,workYear);
    };
    //App修改工作经验
    @Transactional
    public void updateExperience(String userId,String resumeName,String expId,String yearStart,String yearEnd,String companyName,String job,String jobDescribe){
        System.out.println("dao层修改工作经验......");
        System.out.println("expId..........."+expId);
        experienceDao.updateExperience(userId,resumeName,expId,yearStart,yearEnd,companyName,job,jobDescribe);
    }
    //App创建工作经验
    @Transactional
    @Override
    public void createExperience(String userId, String resumeName, String yearStart, String yearEnd, String companyName, String job, String jobDescribe) {
        experienceDao.createExperience(userId,resumeName,yearStart,yearEnd,companyName,job,jobDescribe);
    }

    //App修改教育经历
    @Transactional
    public void updateEducation(String userId,String resumeName,String eduId,String yearStart,String yearEnd,String school,String major ){
        educationDao.updateEducation(userId,resumeName,eduId,yearStart,yearEnd,school,major);
    }
    //App创建教育经历
    @Transactional
    @Override
    public void createEducation(String userId, String resumeName, String yearStart, String yearEnd, String school, String major) {
        educationDao.createEducation(userId,resumeName,yearStart,yearEnd,school,major);
    }

    //App修改求职意向
    @Transactional
    public void updateJobIntent(String userId,String resumeName,String city,String job,String intentSalary,String arriveTime,String selfEvaluation, String selfTag){
        jobIntentDao.updateJonIntent(userId,resumeName,city,job,intentSalary,arriveTime,selfEvaluation,selfTag);
    }
    //App修改项目经验
    @Transactional
    public void updateProjectExperience(String userId,String resumeName,String proExpId,String timeStart,String timeEnd, String companyName,String projectName,String projectDescribe){
        projectExperienceDao.updateProjectExperience(userId,resumeName,proExpId,timeStart,timeEnd,companyName,projectName,projectDescribe);
    }
    //App创建项目经验
    @Transactional
    @Override
    public void createProjectExperience(String userId, String resumeName, String timeStart, String timeEnd, String companyName, String projectName, String projectDescribe) {
        projectExperienceDao.createProjectExperience(userId,resumeName,timeStart,timeEnd,companyName,projectName,projectDescribe);
    }

    //App修改教育及工作情况表
    @Transactional
    public void updateEduJobCondition(String userId,String resumeName,String eduJobConId, String times, String conDescribe ){
        eduJobConditionDao.updateEduJobCondition(userId,resumeName,eduJobConId,times,conDescribe);
    }
    //App创建教育及工作情况
    @Transactional(value = "myTransaction", propagation = Propagation.REQUIRED, rollbackFor={Exception.class}, isolation = Isolation.DEFAULT)
    @Override
    public void createEduJobCondition(String userId, String resumeName, String times, String conDescribe) {
            eduJobConditionDao.createEduJobCondition(userId,resumeName,times,conDescribe);
    }

    //创建简历 (同时创建求职意向)
    @Transactional
    public void  createResume(String userId,String resumeName,String isVisible){
        resumeDao.createResume(userId,resumeName,isVisible);
        jobIntentDao.createJobIntent(userId,resumeName);
    };

    //PC查看审核通过的简历
    @Override
    public List<DivResume> checkSuccessResume(String companyId) {
        List<DivResume> successResumes = resumeDao.checkSuccessResume(companyId);
        return successResumes;
    }
}
