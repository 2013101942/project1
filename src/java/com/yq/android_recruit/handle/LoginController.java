package com.yq.android_recruit.handle;

import com.yq.android_recruit.component.Page;
import com.yq.android_recruit.pojo.DivAppRecruitInfo;
import com.yq.android_recruit.pojo.DivRecruitInfo;
import com.yq.android_recruit.pojo.DivResume;
import com.yq.android_recruit.pojo.User;
import com.yq.android_recruit.service.IDivResumeService;
import com.yq.android_recruit.service.IRecruitInfoService;
import com.yq.android_recruit.service.IUserService;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录验证,此为webService入口，浏览器和App都能访问
 */
@Controller
@RequestMapping(value = "/android_recruit")
public class LoginController extends BaseHandle {
    @Resource(name = "userService")
    private IUserService userService;
    @Resource(name = "recruitInfo")
    private IRecruitInfoService recruitInfoService ;
    @Resource(name = "divResume")
    private IDivResumeService divResumeService;
    //pc登录，根据前端的action判断走哪条线
    @RequestMapping(value = "/login.do" , method = RequestMethod.GET)
    public String   login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request, HttpSession session){
        //先验证账号
        String result = this.userService.validateUser(userName, password);
        String kind = userService.getUser(userService.getUserId(userName)).getKind();
        System.out.println("result:"+result);
        String status = "" ;

        if (result.equals("用户名为空")){
            System.out.println("用户名为空");
            status = "用户名为空";
        }else if (result.equals("密码为空")){
            System.out.println("密码为空");
            status = "密码为空";
        }else if (result.equals("用户不存在") || "0".equals(kind)){
            System.out.println("用户不存在");
            status = "用户不存在";
        }else if (result.equals("用户名或密码错误")){
            System.out.println("用户名或密码错误");
            status = "用户名或密码错误";
        }else if (result.equals("success")){
            System.out.println("success");
            status = "success";
        }
        //如果登录不成功
        if (!status.equals("success")){
            System.out.println("不成功");
            request.setAttribute("status",status);
            return "/sys/login.jsp";
        }
        //如果登录成功
        String userId = userService.getUserId(userName);
        User user = userService.getUser(userId);
        System.out.println("userId"+userId);

        //获取某公司招聘岗位
        List<String> jobs = recruitInfoService.getJobs(userId);
        //保存用户名、密码以及招聘岗位在session
        session.setAttribute("userId",userId);
        session.setAttribute("companyName",userName);
        System.out.println("公司名称："+session.getAttribute("companyName"));
        session.setAttribute("password",user.getPassword());
        session.setAttribute("jobs",jobs);

        request = getPage(request,session);
        return "/page.jsp";
    }

    public HttpServletRequest getPage(HttpServletRequest request,HttpSession session){
        DivResume divResume = new DivResume();

        divResume.setJob(request.getParameter("job"));
        divResume.setGender(request.getParameter("gender"));
        divResume.setAge(request.getParameter("age"));
        divResume.setQualifications(request.getParameter("qualifications"));
        int pageRecord = 3;
        if (request.getParameter("pageRecord") != null && !"".equals(request.getParameter("pageRecord"))){
            pageRecord = Integer.valueOf(request.getParameter("pageRecord"));
        }
        Page<DivResume> divResumePage = new Page<DivResume>(pageRecord,divResume);
        if (request.getParameter("pageNum") != null && !"".equals(request.getParameter("pageNum"))){
            divResumePage.setPageNum(Integer.valueOf(request.getParameter("pageNum")));
        }
        System.out.println("前台pageNum..............."+request.getParameter("pageNum"));

        System.out.println("start..............."+divResumePage.getStart());
        System.out.println("pageNum..............."+divResumePage.getPageNum());
        System.out.println("end..............."+divResumePage.getEnd());
        divResumeService.divResumePage(divResumePage);

        session.setAttribute("page",divResumePage);
        return request;
    }

    //分页，查询简历
    @RequestMapping("/page.do")
    public String page(HttpServletRequest request,HttpSession session){
        request = getPage(request,session);
        return "/page.jsp";
    }

    //app登录验证
    @RequestMapping(value = "/appLogin.do/{userName}/{password}" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object appLogin(@PathVariable String userName,@PathVariable String password){
        System.out.println("用户名："+userName);
        System.out.println("密码："+password);
        //先验证账号
        String result = this.userService.validateUser(userName, password);
        String kind = userService.getUser(userService.getUserId(userName)).getKind();
        String status = "" ;
        if (result.equals("用户不存在") || "1".equals(kind)){
            System.out.println("用户不存在");
            status = "用户不存在";
        }else if (result.equals("用户名或密码错误")){
            System.out.println("用户名或密码错误");
            status = "用户名或密码错误";
        }else if (result.equals("success")){
            System.out.println("success");
            status = "success";
        }

        if (!status.equals("success")){
            //如果登录不成功，则返回null
            return status;
        }else{
            System.out.println("下面是personId....");
            //移动端登录，查招聘信息
            DivAppRecruitInfo recruitInfo = new DivAppRecruitInfo();
            Page<DivAppRecruitInfo> recruitInfoPage = new Page<>(10,recruitInfo);
            String personId = userService.getUserId(userName);
            System.out.println("personId...."+personId);
            recruitInfoPage.setUserId(personId);
            //page方法
            recruitInfoService.recruitInfoAppPage(recruitInfoPage);
            //获取简历
            divResumeService.getUserResumeList(recruitInfoPage);
            return recruitInfoPage;
        }
    }



    //App端获取招聘信息 (搜索功能)
    @RequestMapping(value = "/appRecruitInfoPage" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Page<DivAppRecruitInfo> getDivAppRecruitInfo(String property, String job, String salaryStart,String salaryEnd){

        DivAppRecruitInfo divRecruitInfo = new DivAppRecruitInfo();
        if (property != null && !"".equals(property)){
            divRecruitInfo.seteProperty(property);
        }
        if (job != null && !"".equals(job)){
            divRecruitInfo.setJob(job);
        }
        if (salaryStart != null && !"".equals(salaryStart)){
            divRecruitInfo.setSalaryStart(Integer.valueOf(salaryStart));
        }
        if (salaryEnd != null && !"".equals(salaryEnd)){
            divRecruitInfo.setSalaryEnd(Integer.valueOf(salaryEnd));
        }
        System.out.println("property......."+divRecruitInfo.geteProperty());
        System.out.println("job......."+divRecruitInfo.getJob());
        System.out.println("salaryStart........."+divRecruitInfo.getSalaryStart());
        System.out.println("salaryEnd........"+divRecruitInfo.getSalaryEnd());
        Page<DivAppRecruitInfo> recruitInfoPage = new Page<DivAppRecruitInfo>(10,divRecruitInfo);

        //page方法
        recruitInfoService.recruitInfoAppPage(recruitInfoPage);
        return recruitInfoPage;
    }

}
