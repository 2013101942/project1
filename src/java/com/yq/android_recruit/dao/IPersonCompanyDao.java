package com.yq.android_recruit.dao;

import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.DivResume;
import com.yq.android_recruit.pojo.PersonCompany;

import java.util.List;

public interface IPersonCompanyDao {
    void setPersonCompany(PersonCompany personCompany);
    void setPersonCompanyTwo(PersonCompany personCompany);
    void sendMsgPC(String personId,String companyId,String resumeName,String msg);
    void sendMsgApp(String personId,String companyId,String recruitId, String msg);
    List<PersonCompany> getPcSendMsg(String companyId);
    List<PersonCompany> getPcReceivedMsg(String companyId);
    //App查看已收到的留言
    List<PersonCompany> getAppReceivedMsg(String personId);
    //App查看已发送的留言
    List<PersonCompany> getAppSendedMsg(String personId);
    void adopt(String company,String personId,String resumeName);

    List<DivAppRecruitInfo> getAdoptedRecruit(String personId);

    //App修改简历名
    void updateResumeName(String userId,String oldResumeName,String newResumeName);
    //App取消投递简历
    void cancelApply(String personId,String companyId,String companyToPerson,String recruitId);
    //因删除了某条招聘信息而删除某条记录
    void deletePersonCompanyPC(String companyId,String recruitId);
    //因删除了某条简历而删除某条记录
    void deletePersonCompanyApp(String personId,String resumeName);
}
