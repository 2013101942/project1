package com.yq.android_recruit.dao;

import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.RecruitInfo;

import java.util.List;

public interface IRecruitInfoDao {
    RecruitInfo getRecruitInfo(String recruitId, String company);
    //查询某公司招聘的岗位
    List<String> getMyJobs(String companyId);
    //查询所有岗位
    List<String> getJobs(String companyId);
    List<String> getJobs();

    //根据job查询recruitId
    String getRecruitId(String companyId,String job);

    int getPageCount(Page<DivRecruitInfo> page);
    List<DivRecruitInfo> getDivRecruitInfo(Page<DivRecruitInfo> page);
    void releaseRecruit(String companyId,String job,String staff_num,String salaryStart, String salaryEnd,String recruitInfo,String experience);
    List<DivAppRecruitInfo> getApplyRecruit(String personId);

    //某公司查看自己发布过的招聘信息
    List<DivRecruitInfo> getMyRecruitInfo(String companyId);
    //某公司修改自己某条招聘信息
    void updateMyRecruitInfo(String userId,String recruitId, String job, String staffNum, String salaryStart, String salaryEnd, String recruitInfo, String experience);
    //公司删除某条招聘信息
    void deleteMyRecruit(String userId,String recruitId);

    Integer getAppPageCount(Page<DivAppRecruitInfo> page);

    List<DivAppRecruitInfo> getDivAppRecruitInfo(Page<DivAppRecruitInfo> page);
}
