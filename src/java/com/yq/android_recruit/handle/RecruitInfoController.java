package com.yq.android_recruit.handle;

import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.RecruitInfo;
import com.yq.android_recruit.service.IPersonCompanyService;
import com.yq.android_recruit.service.IRecruitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Recruit")
public class RecruitInfoController {

    @Autowired
    private IRecruitInfoService recruitInfoService;
    @Autowired
    private IPersonCompanyService personCompanyService;

    //发布招聘信息（PC端）
    @RequestMapping("/releaseRecruit.do")
    public String releaseRecruit(HttpServletRequest request,@RequestParam("companyId") String companyId,@RequestParam("job") String job,@RequestParam("staffNum") String staffNum,@RequestParam("salaryStart") String salaryStart, @RequestParam("salaryEnd") String salaryEnd,@RequestParam("recruitInfo") String recruitInfo,@RequestParam("experience") String experience){
        System.out.println("招聘人数......"+staffNum);
        recruitInfoService.releaseRecruit(companyId,job,staffNum,salaryStart,salaryEnd,recruitInfo,experience);
        request.setAttribute("releaseResult","releaseRecruit success");
        System.out.println("放了releaseResult......"+request.getAttribute("releaseResult"));
        return "/releaseRecruit.jsp";
    }

    //App查看已申请的招聘信息 ( app端 )
    @RequestMapping("/checkApplyRecruit.do/{personId}")
    @ResponseBody
    public List<DivAppRecruitInfo> getApplyRecruit(@PathVariable String personId){
        List<DivAppRecruitInfo> applyRecruit = recruitInfoService.getApplyRecruit(personId);
        return applyRecruit;
    }

    //App查看已通过的招聘信息
    @RequestMapping("/checkAdoptedRecruit.do/{personId}")
    @ResponseBody
    public List<DivAppRecruitInfo> getAdoptedRecruit(@PathVariable String personId){
        List<DivAppRecruitInfo> adoptedRecruit = personCompanyService.getAdoptedRecruit(personId);
        System.out.println("已通过的招聘信息...."+adoptedRecruit.size());
        System.out.println("最后一条的岗位是...."+adoptedRecruit.get(adoptedRecruit.size()-1).getJob());
        return adoptedRecruit;
    }

    //PC查看自己发布过的招聘信息
    @RequestMapping("/checkMyRecruitInfo.do")
    public String getMyRecruitInfo(HttpSession session, HttpServletRequest request){
        String userId = (String)session.getAttribute("userId");
        List<DivRecruitInfo> myRecruitInfo = recruitInfoService.getMyRecruitInfo(userId);
        session.setAttribute("myRecruitInfo",myRecruitInfo);
        return "/recruitInfo.jsp";
    }

    //公司更改招聘信息
    @RequestMapping("/updateMyRecruitInfo.do")
    public String updateMyRecruitInfo(HttpSession session ,HttpServletRequest request,@RequestParam("userId") String userId,@RequestParam("recruitId") String recruitId,@RequestParam("job") String job,@RequestParam("staffNum") String staffNum,@RequestParam("salaryStart") String salaryStart,@RequestParam("salaryEnd") String salaryEnd,@RequestParam("recruitInfo") String recruitInfo,@RequestParam("experience") String experience){

        System.out.println(userId);
        System.out.println(recruitId);
        System.out.println(job);
        System.out.println(staffNum);
        System.out.println(salaryStart);
        System.out.println(salaryEnd);
        System.out.println(recruitInfo);
        System.out.println(experience);
        recruitInfoService.updateMyRecruitInfo(userId,recruitId,job,staffNum,salaryStart,salaryEnd,recruitInfo,experience);
        String  state = "update success";
        request.setAttribute("state",state);
        List<DivRecruitInfo> myRecruitInfo = recruitInfoService.getMyRecruitInfo(userId);
        session.setAttribute("myRecruitInfo",myRecruitInfo);
        return "/recruitInfo.jsp";
    }

    //PC公司删除某条招聘消息 ( 要删除所有表与此相关的记录 )
    @RequestMapping("/deleteMyRecruitInfo.do")
    public String deleteMyRecruitInfo(@RequestParam("recruitId") String recruitId, HttpServletRequest request,HttpSession session){
        String userId = (String) session.getAttribute("userId");
            System.out.println("controller里有没有recruitId"+recruitId);
        recruitInfoService.deleteMyRecruit(userId,recruitId);
        String  state = "delete success";
        request.setAttribute("state",state);
        List<DivRecruitInfo> myRecruitInfo = recruitInfoService.getMyRecruitInfo(userId);
        session.setAttribute("myRecruitInfo",myRecruitInfo);
        return "/recruitInfo.jsp";
    }
}
