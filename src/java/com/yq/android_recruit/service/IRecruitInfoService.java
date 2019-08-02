package com.yq.android_recruit.service;

import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.RecruitInfo;
import com.yq.android_recruit.pojo.Resume;

import java.util.List;

public interface IRecruitInfoService {
    void recruitInfoPage(Page<DivRecruitInfo> page);
    void recruitInfoAppPage(Page<DivAppRecruitInfo> page);
    void releaseRecruit(String companyId,String job,String staff_num,String salaryStart, String salaryEnd,String recruitInfo,String exprience);
    List<DivAppRecruitInfo> getApplyRecruit(String personId);
    List<String> getJobs(String companyId);
    //公司查看自己发布过的招聘信息
    List<DivRecruitInfo> getMyRecruitInfo(String companyId);
    //公司更改某条招聘信息
    void updateMyRecruitInfo(String userId,String recruitId,String job,String staffNum,String salaryStart,String salaryEnd,String recruitInfo,String experience);
    //公司删除某条招聘信息
    void deleteMyRecruit(String userId,String recruitId);

}
