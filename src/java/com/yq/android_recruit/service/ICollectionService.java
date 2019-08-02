package com.yq.android_recruit.service;

import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.DivResume;

import java.util.List;

public interface ICollectionService {
    //PC收藏
    void  collectPC(String personId,String companyId,String company_add_person,String resumeName,String recruitId);
    //App收藏
    void  collectApp(String personId,String companyId,String company_add_person,String resumeName,String recruitId);

    //PC端取消收藏
    void cancelCollectPC(String personId,String companyId, String companyAddResume,String resumeName);
    //App端取消收藏
    void cancelCollectApp(String personId,String companyId, String companyToPerson,String recruitId);
    //查看收藏的简历
    List<DivResume> getCollectResume(String companyId);
    //查看收藏了的招聘信息
    List<DivAppRecruitInfo> getCollectRecruit(String personId);
}
