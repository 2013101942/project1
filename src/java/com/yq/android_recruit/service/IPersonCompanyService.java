package com.yq.android_recruit.service;

import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.DivResume;
import com.yq.android_recruit.pojo.PersonCompany;

import java.util.List;

public interface IPersonCompanyService {
    //投递简历
    void sendResume(String personId, String companyId,String companyToPerson,String message,String resumeName,String recruitId);
    //发送邀请
    void sendInvitation(String personId, String companyId,String companyToPerson,String message,String resumeName,String recruitId);
    //App发送留言(不论是否通过，只要建立了关系就可以发送留言)
    void sendMsgApp(String personId, String companyId,String recruitId, String msg);
    //PC发送留言
    void sendMsgPC(String personId, String companyId,String resumeName,String msg);
    //获取留言
    List<PersonCompany> getPcSendMsg(String companyId);
    List<PersonCompany> getPcReceivedMsg(String companyId);
    List<PersonCompany> getAppReceivedMsg(String personId);
    //App查看已发送的留言
    List<PersonCompany> getAppSendedMsg(String personId);
    //采纳某个简历
    void adopt(String companyId,String personId,String resumeName);
    List<DivAppRecruitInfo> getAdoptedRecruit(String personId);
    //app取消投递简历
    void notApply(String personId,String companyId,String companyToPerson,String recruitId);


}
