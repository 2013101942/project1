package com.yq.android_recruit.handle;

import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivResume;
import com.yq.android_recruit.service.IDivResumeService;
import com.yq.android_recruit.service.IPersonCompanyService;
import com.yq.android_recruit.service.IRecruitInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/Resume.do")
public class ResumeController {

    @Resource(name = "personCompany")
    private IPersonCompanyService personCompanyService;
    @Resource(name = "divResume")
    private IDivResumeService divResumeService;
    @Resource(name = "recruitInfo")
    private IRecruitInfoService recruitInfoService;
    //查看收到的简历（PC端）
    @RequestMapping("/receivedResumeList")
    public String checkReceivedResume(HttpSession session, HttpServletRequest request){
        System.out.println("查看收到的招聘简历userId"+session.getAttribute("userId"));
        List<DivResume> receivedResume = divResumeService.getReceivedResume((String) session.getAttribute("userId"));
        session.setAttribute("receivedResume",receivedResume);
        return "/received_resume.jsp";
    }
    //通过正在查看的简历(PC端)
    @RequestMapping("/adopt")
    public String adoptReceiveResume(HttpSession session,HttpServletRequest request,@RequestParam("companyId") String companyId,@RequestParam("personId") String personId,@RequestParam("resumeName") String resumeName){
        personCompanyService.adopt(companyId,personId,resumeName);
        List<DivResume> successResumes = divResumeService.checkSuccessResume(companyId);
        session.setAttribute("successResumes",successResumes);
        request.setAttribute("adoptStatus","adopt success");
        List<DivResume> receivedResume = divResumeService.getReceivedResume((String) session.getAttribute("userId"));
        session.setAttribute("receivedResume",receivedResume);
        return "/received_resume.jsp";
    }
    //PC查看审核通过的简历
    @RequestMapping("/checkSuccessResume")
    public String checkSuccessResume(HttpSession session, HttpServletRequest request){
        String userId = (String) session.getAttribute("userId");
        System.out.println("查看审核通过的简历UserId....."+userId);
        List<DivResume> successResumes = divResumeService.checkSuccessResume(userId);
        session.setAttribute("successResumes",successResumes);
        return "/success_resume.jsp";
    }

    //app投递简历(移动端访问)
    @RequestMapping(value = "/apply/{personId}/{companyId}/{recruitId}/{resumeName}" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendApply(@PathVariable String personId,@PathVariable String companyId,@PathVariable String recruitId,@PathVariable String resumeName){
        personCompanyService.sendResume(personId,companyId,"0","",resumeName,recruitId);
        return "deliver success";
    }
    //App取消投递（移动端,不需要知道简历名,不可能一个招聘信息投两个简历）
    @RequestMapping(value = "/notApply/{personId}/{companyId}/{recruitId}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String notApply(@PathVariable String personId,@PathVariable String companyId,@PathVariable String recruitId){
        System.out.println("取消投递.................");
        personCompanyService.notApply(personId,companyId,"0",recruitId);
        return "notApply success";
    }


    //app创建简历
    @RequestMapping(value = "/createResume.do/{userId}/{resumeName}/{isVisible}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Page<DivAppRecruitInfo> createResume(@PathVariable String userId,@PathVariable String resumeName,@PathVariable String isVisible){
        //创建简历
        divResumeService.createResume(userId,resumeName,isVisible);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(userId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }

    //app删除简历
    @RequestMapping(value = "/deleteResume.do/{personId}/{resumeName}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> deleteResume(@PathVariable String personId,@PathVariable String resumeName){
        divResumeService.deleteResume(personId,resumeName);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        System.out.println("删除了简历后recruitInfoPage.........."+recruitInfoPage.getDetailedResumes().get(0).getResumeBean().getResumeName());
        return recruitInfoPage;
    }
    //编辑简历设置信息
    @RequestMapping(value = "/updateResumeSetting.do/{userId}/{oldResumeName}/{newResumeName}/{isVisible}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> updateResumeSetting(@PathVariable String userId,@PathVariable String oldResumeName,@PathVariable String newResumeName,@PathVariable String isVisible){
        divResumeService.updateResumeSetting(userId,oldResumeName,newResumeName,isVisible);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(userId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }
    //编辑简历基本信息
    @RequestMapping(value = "/updateResumeBaseInfo.do/{personId}/{resumeName}/{gender}/{birthday}/{age}/{tel}/{state}/{city}/{qualification}/{workYear}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> updateResumeBaseInfo(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String gender,@PathVariable String birthday,@PathVariable String age,@PathVariable String tel,@PathVariable String state,@PathVariable String city,@PathVariable String qualification,@PathVariable String workYear){
        System.out.println("personId......."+personId);
        System.out.println("resumeName......."+resumeName);
        System.out.println("gender......."+gender);
        System.out.println("birthday......."+birthday);
        System.out.println("age......."+age);
        System.out.println("tel......."+tel);
        System.out.println("state......."+state);
        System.out.println("qualification......."+qualification);
        System.out.println("workYear......."+workYear);
        divResumeService.updateResumeBaseInfo(personId,resumeName,gender,birthday,age,tel,state,city,qualification,workYear);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }
    //编辑工作经验
    @RequestMapping(value = "/updateExperience.do/{personId}/{resumeName}/{experienceId}/{yearStart}/{yearEnd}/{companyName}/{job}/{jobDescribe}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> updateExperience(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String experienceId,@PathVariable String yearStart,@PathVariable String yearEnd,@PathVariable String companyName,@PathVariable String job,@PathVariable String jobDescribe){
        System.out.println("编辑工作经验.........");
        divResumeService.updateExperience(personId,resumeName,experienceId,yearStart,yearEnd,companyName,job,jobDescribe);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }
    //创建工作经验
    @RequestMapping(value = "/createExperience.do/{personId}/{resumeName}/{yearStart}/{yearEnd}/{companyName}/{job}/{jobDescribe}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> createExperience(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String yearStart,@PathVariable String yearEnd,@PathVariable String companyName,@PathVariable String job,@PathVariable String jobDescribe){
        System.out.println("进来了createExperience.........");
        divResumeService.createExperience(personId,resumeName,yearStart,yearEnd,companyName,job,jobDescribe);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }

    //编辑教育经历
    @RequestMapping(value = "/updateEducation.do/{personId}/{resumeName}/{eduId}/{yearStart}/{yearEnd}/{school}/{major}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> updateEducation(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String eduId,@PathVariable String yearStart,@PathVariable String yearEnd,@PathVariable String school,@PathVariable String major){
        divResumeService.updateEducation(personId,resumeName,eduId,yearStart,yearEnd,school,major);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }
    //创建教育经历
    @RequestMapping(value = "/createEducation.do/{personId}/{resumeName}/{yearStart}/{yearEnd}/{school}/{major}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> createEducation(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String yearStart,@PathVariable String yearEnd,@PathVariable String school,@PathVariable String major){
        divResumeService.createEducation(personId,resumeName,yearStart,yearEnd,school,major);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }

    //编辑求职意向
    @RequestMapping(value = "/updateJobIntent.do/{personId}/{resumeName}/{city}/{job}/{intentSalary}/{arriveTime}/{selfEvaluation}/{selfTag}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private   Page<DivAppRecruitInfo> updateIntentJob(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String city,@PathVariable String job,@PathVariable String intentSalary,@PathVariable String arriveTime,@PathVariable String selfEvaluation,@PathVariable String selfTag){
        System.out.println("求职意向..............");
        divResumeService.updateJobIntent(personId,resumeName,city,job,intentSalary,arriveTime,selfEvaluation,selfTag);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }

    //编辑项目经验
    @RequestMapping(value = "/updateProExp.do/{personId}/{resumeName}/{proExpId}/{timeStart}/{timeEnd}/{companyName}/{projectName}/{proDescribe}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private  Page<DivAppRecruitInfo> updateProjectExperience(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String proExpId,@PathVariable String timeStart,@PathVariable String timeEnd,@PathVariable String companyName,@PathVariable String projectName,@PathVariable String proDescribe){
        divResumeService.updateProjectExperience(personId,resumeName,proExpId,timeStart,timeEnd,companyName,projectName,proDescribe);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }
    //创建项目经验
    @RequestMapping(value = "/createProExp.do/{personId}/{resumeName}/{timeStart}/{timeEnd}/{companyName}/{projectName}/{proDescribe}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> createProjectExperience(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String timeStart,@PathVariable String timeEnd,@PathVariable String companyName,@PathVariable String projectName,@PathVariable String proDescribe){
        divResumeService.createProjectExperience(personId,resumeName,timeStart,timeEnd,companyName,projectName,proDescribe);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        System.out.println("创建项目经验........"+recruitInfoPage.getDetailedResumes().get(0).getResumeBean().getResumeName());
        return recruitInfoPage;
    }

    //编辑在校及工作情况
    @RequestMapping(value = "/updateEduJobCondition.do/{personId}/{resumeName}/{eduJobConId}/{times}/{conDescribe}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> updateEduJobCondition(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String eduJobConId,@PathVariable String times,@PathVariable String conDescribe){
        divResumeService.updateEduJobCondition(personId,resumeName,eduJobConId,times,conDescribe);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }
    //创建在校及工作情况
    @RequestMapping(value = "/createEduJobCondition.do/{personId}/{resumeName}/{times}/{conDescribe}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    private Page<DivAppRecruitInfo> createEduJobCondition(@PathVariable String personId,@PathVariable String resumeName,@PathVariable String times,@PathVariable String conDescribe){
        divResumeService.createEduJobCondition(personId,resumeName,times,conDescribe);
        //page方法,重新获取page数据
        DivAppRecruitInfo divAppRecruitInfo = new DivAppRecruitInfo();
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divAppRecruitInfo);
        recruitInfoPage.setUserId(personId);
        //获取招聘信息
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        //获取简历
        divResumeService.getUserResumeList(recruitInfoPage);
        return recruitInfoPage;
    }
}
