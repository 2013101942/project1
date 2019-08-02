package com.yq.android_recruit.service.impl;

import com.yq.android_recruit.dao.ICollectionDao;
import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.DivResume;
import com.yq.android_recruit.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("collection")
public class CollectionServiceImpl implements ICollectionService {

    @Autowired
    private ICollectionDao collectionDao;
    //收藏（App端）
    @Override
    public void collectApp(String personId,String companyId, String company_add_person, String resumeName,String recruitId) {
        collectionDao.addToCollectionApp(personId,companyId,company_add_person,resumeName,recruitId);
    }
    //收藏（PC端）
    @Override
    public void collectPC(String personId,String companyId, String company_add_person, String resumeName,String recruitId) {
        System.out.println("收藏Dao中personId..."+personId);
        System.out.println("收藏Dao中companyId..."+companyId);
        System.out.println("收藏Dao中company_add_person..."+company_add_person);
        System.out.println("收藏Dao中resumeName..."+resumeName);
        System.out.println("收藏Dao中recruitId..."+recruitId);
        collectionDao.addToCollectionPC(personId,companyId,company_add_person,resumeName,recruitId);
    }
    //取消收藏(PC端)
    @Override
    public void cancelCollectPC(String personId, String companyId,String companyAddResume,  String resumeName) {
        collectionDao.cancelCollectPC(personId,companyId,companyAddResume,resumeName);
    }
    //取消收藏（App端）
    @Override
    public void cancelCollectApp(String personId, String companyId,String companyToPerson,  String recruitId) {
        System.out.println("service取消收藏.........");
        System.out.println("personId..."+personId);
        System.out.println("companyId..."+companyId);
        System.out.println("companyToPerson..."+companyToPerson);
        System.out.println("recruitId..."+recruitId);
        collectionDao.cancelCollectApp(personId,companyId,companyToPerson,recruitId);
    }
    //查看收藏的简历(pc端)
    @Override
    public List<DivResume> getCollectResume(String companyId) {
        List<DivResume> collectResume = collectionDao.getCollectResume(companyId);
        return collectResume;
    }

    //查看收藏了的招聘信息(app)
    @Override
    public List<DivAppRecruitInfo> getCollectRecruit(String personId){
        List<DivAppRecruitInfo> collectRecruit = collectionDao.getCollectRecruit(personId);
        return collectRecruit;
    }
}
