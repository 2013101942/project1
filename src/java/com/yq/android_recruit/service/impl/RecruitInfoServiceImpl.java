package com.yq.android_recruit.service.impl;

import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.dao.ICollectionDao;
import com.yq.android_recruit.dao.IPersonCompanyDao;
import com.yq.android_recruit.dao.IRecruitInfoDao;
import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.RecruitInfo;
import com.yq.android_recruit.service.IRecruitInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recruitInfo")
public class RecruitInfoServiceImpl implements IRecruitInfoService {

    @Autowired
    private IRecruitInfoDao recruitInfoDao;
    @Autowired
    private ICollectionDao collectionDao;
    @Autowired
    private IPersonCompanyDao personCompanyDao;
    //URL传过来的只是部分bean信息，不需要完善bean信息，只是按照bean里有的条件查询
    @Override
    public void  recruitInfoPage(Page<DivRecruitInfo> page) {
        page.setRecordTotal(recruitInfoDao.getPageCount(page));
        page.setDivBeans(recruitInfoDao.getDivRecruitInfo(page));
        page.setJobs(recruitInfoDao.getJobs());
    }

    @Override
    public void recruitInfoAppPage(Page<DivAppRecruitInfo> page) {
        page.setRecordTotal(recruitInfoDao.getAppPageCount(page));
        page.setDivBeans(recruitInfoDao.getDivAppRecruitInfo(page));
        page.setJobs(recruitInfoDao.getJobs());
    }

    //发布招聘信息
    @Override
    public void releaseRecruit(String companyId,String job,String staff_num,String salaryStart, String salaryEnd,String recruitInfo,String exprience){
        recruitInfoDao.releaseRecruit(companyId,job,staff_num, salaryStart,  salaryEnd, recruitInfo, exprience);
    }
    //App查询已申请的招聘信息
    @Override
    public List<DivAppRecruitInfo> getApplyRecruit(String personId) {
        List<DivAppRecruitInfo> applyRecruit = recruitInfoDao.getApplyRecruit(personId);
        return applyRecruit;
    }

    //查询某公司发布过的招聘信息
    @Override
    public List<String> getJobs(String companyId){
        List<String> jobs = recruitInfoDao.getJobs(companyId);
        return jobs;
    }

    @Override
    public List<DivRecruitInfo> getMyRecruitInfo(String companyId) {
        List<DivRecruitInfo> myRecruitInfo = recruitInfoDao.getMyRecruitInfo(companyId);
        return myRecruitInfo;
    }
    //公司修改某条招聘信息
    @Override
    public void updateMyRecruitInfo(String userId, String recruitId,String job, String staffNum, String salaryStart, String salaryEnd, String recruitInfo, String experience) {
        recruitInfoDao.updateMyRecruitInfo(userId,recruitId,job,staffNum,salaryStart,salaryEnd,recruitInfo,experience);
    }
    //公司删除某条招聘消息（要删除所有表中与此相关的记录）
    @Override
    public void deleteMyRecruit(String userId, String recruitId) {
        recruitInfoDao.deleteMyRecruit(userId,recruitId);
        collectionDao.deleteCollectionPC(userId,recruitId);
        personCompanyDao.deletePersonCompanyPC(userId,recruitId);
        System.out.println("userId"+userId);
        System.out.println("recruitId"+recruitId);
        System.out.println("走了删除方法......");
    }
}
