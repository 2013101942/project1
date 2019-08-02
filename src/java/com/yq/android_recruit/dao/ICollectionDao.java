package com.yq.android_recruit.dao;

import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.DivResume;

import java.util.List;

public interface ICollectionDao {
    //PC收藏
    void addToCollectionPC(String personId, String companyId, String company_add_person, String resumeName,String recruitId);
    //App收藏
    void addToCollectionApp(String personId, String companyId, String company_add_person, String resumeName,String recruitId);
    //取消收藏PC
    void cancelCollectPC(String personId,String companyId, String companyAddResume,String resumeName);
    //取消收藏App
    void cancelCollectApp(String personId,String companyId, String companyAddResume,String recruitId);
    //查看收藏的简历
    List<DivResume> getCollectResume(String companyId);
    //查看收藏了的招聘信息
    List<DivAppRecruitInfo> getCollectRecruit(String personId);

    //App修改简历名
    void updateResumeName(String userId,String oldResumeName,String newResumeName);

    //因某条招聘信息删除而删除某条收藏
    void deleteCollectionPC(String companyId,String recruitId);
    //因删除某个简历而删除某条记录
    void deleteCollectionApp(String personId,String resumeName);
}
