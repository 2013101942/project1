package com.yq.android_recruit.handle;

import com.yq.android_recruit.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource(name = "userService")
    private IUserService userService;

    //注册（App端 ）
    @RequestMapping("/registerApp.do/{userName}/{password}/{kind}")
    @ResponseBody
    public String registerApp(@PathVariable String userName,@PathVariable String password,@PathVariable String kind){
        //验证是否已存在
        boolean isExist = userService.userIsExist(userName);
        if (isExist){
            return "user isExisted";
        }else {
            userService.register(userName,password, kind);
            return "register success";
        }
    }
    //注册（PC端 ）
    @RequestMapping("/registerPC.do")
    public String registerPC(HttpServletRequest request, @RequestParam("userName") String userName, @RequestParam("password") String password){
        String status = null;
        if (userName == null || "".equals(userName)){
            status = "用户名不能为空";

        }else  if (password == null || "".equals(password)){
            status = "密码不能为空";

        }else if (userService.userIsExist(userName)){
            status = "该用户已存在";

        }else {
            status = "register success";
        }
        if (!"register success".equals(status)){
            request.setAttribute("registerStatus",status);
            return "/sys/register.jsp";
        }
        request.setAttribute("registerStatus",status);
        userService.register(userName,password, "1");
        return "/sys/login.jsp";
    }
}
