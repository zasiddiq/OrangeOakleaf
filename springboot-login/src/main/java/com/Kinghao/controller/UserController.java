package com.Kinghao.controller;

import com.Kinghao.bean.Result;
import com.Kinghao.bean.User;
import com.Kinghao.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/regist")
    @ResponseBody
    public Result regist(User user){
        logger.trace("login was request");
        return userService.regist(user);
    }

    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(User user, HttpServletRequest request){
        logger.trace("login was request");
        Result R1=userService.login(user);
        if(R1.isSuccess()){
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            R1.setMsg(user.getUsername()+", Hello!");
        }
        return R1;
    }
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}

