package com.yq.android_recruit.service.impl;

import com.yq.android_recruit.dao.*;
import com.yq.android_recruit.pojo.*;
import com.yq.android_recruit.service.IDetailedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//PC端和移动端用不同的方法
@Service("detailed")
public class DetailedServiceImpl implements IDetailedService {

    @Autowired
    private IResumeDao resumeDao;
    @Autowired
    private IExperienceDao experienceDao;
    @Autowired
    private IEducationDao educationDao;
    @Autowired
    private IJobIntentDao jobIntentDao;
    @Autowired
    private IProjectExperienceDao projectExprienceDao;
    @Autowired
    private IEduJobConditionDao eduJobConditionDao;

    @Autowired
    private IRecruitInfoDao recruitInfoDao;

    //PC端
    @Override
    public DetailedResume loadDetailedResume(String userId, String resumeName) {
        //取出详细简历中的各个bean
        Resume resume = resumeDao.getResume(userId, resumeName);
        List<Experience> experience = experienceDao.getExperiences(userId,resumeName);
        List<Education> education = educationDao.getEducations(userId,resumeName);
        JobIntent jobIntent = jobIntentDao.getJobIntents(userId,resumeName);
        List<ProjectExprience> projectExprience = projectExprienceDao.getProjectExpriences(userId,resumeName);
        List<EduJobCondition> eduJobCondition = eduJobConditionDao.getEduJobConditions(userId,resumeName);
        //把各个bean打包
        DetailedResume detailedResume = new DetailedResume();
        detailedResume.setResumeBean(resume);
        detailedResume.setExperienceList(experience);
        detailedResume.setEducationList(education);
        detailedResume.setJobIntentBean(jobIntent);
        detailedResume.setProjectExprienceList(projectExprience);
        detailedResume.setEduJobConditionList(eduJobCondition);
        return detailedResume;
    }

    //移动端
    @Override
    public RecruitInfo loadDetailedRecruit(String company,String recruitId){
        RecruitInfo recruitInfo = recruitInfoDao.getRecruitInfo(recruitId, company);
        return recruitInfo;
    }
    //查询某公司所有岗位
    @Override
    public List<String> getMyJobs(String companyId) {
        List<String> myJobs = recruitInfoDao.getMyJobs(companyId);
        return myJobs;
    }


}
