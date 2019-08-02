package com.yq.android_recruit.service.impl;

import com.yq.android_recruit.dao.*;
import com.yq.android_recruit.pojo.*;
import com.yq.android_recruit.service.IPersonCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service("personCompany")
public class PersonCompanyServiceImpl implements IPersonCompanyService {

    @Autowired
    IPersonCompanyDao personCompanyDao;
    @Autowired
    IEnterpriseDao enterpriseDao;
    @Autowired
    IResumeDao resumeDao;
    @Autowired
    IRecruitInfoDao recruitInfoDao;
    //个人投递简历（App端） --- (一旦建立联系，就是一对)
    @Override
    public void sendResume(String personId, String companyId,String companyToPerson, String message,String resumeName,String recruitId) {
        System.out.println("service--company_to_person.............."+companyToPerson);
        System.out.println("service--message.............."+message);
        PersonCompany personCompany = new PersonCompany(companyId, personId, companyToPerson,  message,resumeName,recruitId,"0");
        personCompanyDao.setPersonCompany(personCompany);
        personCompany.setCompanyToPerson("1");
        personCompany.setMessage("");
        personCompanyDao.setPersonCompanyTwo(personCompany);
    }
    //公司邀请简历（PC端） --- (一旦建立联系，就是一对)
    @Override
    public void sendInvitation(String personId, String companyId, String company_to_person,String message,String resumeName,String job) {
        System.out.println("邀请service..."+message);
        System.out.println("company_to_person....."+company_to_person);
        //根据job查询recruitId
        String recruitId = recruitInfoDao.getRecruitId(companyId,job);
        PersonCompany personCompany = new PersonCompany(companyId, personId, company_to_person,  message,resumeName,recruitId,"1");
        personCompanyDao.setPersonCompany(personCompany);
        personCompany.setCompanyToPerson("0");
        personCompany.setMessage("");
        personCompanyDao.setPersonCompanyTwo(personCompany);
    }
    //发送留言 PC端
    @Override
    public void sendMsgPC(String personId, String companyId,String resumeName, String msg) {
            personCompanyDao.sendMsgPC(personId,companyId,resumeName,msg);
    }
    @Override
    public void sendMsgApp(String personId, String companyId,String recruitId, String msg) {
        System.out.println("app发送消息Dao层...");
            personCompanyDao.sendMsgApp(personId,companyId,recruitId,msg);
    }

    //PC查询已发送的留言
    @Override
    public List<PersonCompany> getPcSendMsg(String companyId){
        List<PersonCompany> pcSendMsg = personCompanyDao.getPcSendMsg(companyId);
        return pcSendMsg;
    }
    //PC查看已收到的留言
    @Override
    public List<PersonCompany> getPcReceivedMsg(String companyId) {
        List<PersonCompany> pcReceivedMsg = personCompanyDao.getPcReceivedMsg(companyId);
        return pcReceivedMsg;
    }
    //App查看接收到的留言
    @Override
    public List<PersonCompany> getAppReceivedMsg(String personId) {
        List<PersonCompany> appReceivedMsgList = personCompanyDao.getAppReceivedMsg(personId);
        return appReceivedMsgList;
    }
    //App查看已发送的留言
    @Override
    public List<PersonCompany> getAppSendedMsg(String personId) {
        List<PersonCompany> appSendedMsgList = personCompanyDao.getAppSendedMsg(personId);
        return appSendedMsgList;
    }

    //通过正在查看的简历
    @Override
    public void adopt(String companyId, String personId, String resumeName) {
        personCompanyDao.adopt(companyId,personId,resumeName);
    }
    //查看已通过的招聘信息
    @Override
    public List<DivAppRecruitInfo> getAdoptedRecruit(String personId) {
        List<DivAppRecruitInfo> adoptedRecruit = personCompanyDao.getAdoptedRecruit(personId);
        return adoptedRecruit;
    }
    //App取消投递简历
    @Override
    public void notApply(String personId, String companyId, String companyToPerson, String recruitId) {
        personCompanyDao.cancelApply(personId,companyId,companyToPerson,recruitId);
    }



}
