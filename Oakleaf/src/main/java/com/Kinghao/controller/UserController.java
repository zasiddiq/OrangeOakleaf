package com.Kinghao.controller;

import com.Kinghao.bean.Result;
import com.Kinghao.bean.User;
import com.Kinghao.service.UserService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "User Controller")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    /**
     * Register
     * @param user
     * @return Result
     */
    @PostMapping(value = "/regist")
    @ApiOperation(value="Add a user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "Username--Length up to 255",required = true),
            @ApiImplicitParam(name = "password",value = "Password--Length up to 255",required = true),
            @ApiImplicitParam(name = "userType",value = "Use numbers 1-6 to indicate the user type",required = true)
    })

    @ResponseBody
    public Result regist(User user){
        logger.trace("login was request");
        return userService.regist(user);
    }

    /**
     * Login
     * @param user
     * @return Result
     */
    @PostMapping(value = "/login")
    @ApiOperation("Check the login information of the user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "Username",required = true),
            @ApiImplicitParam(name = "password",value = "Password",required = true)
    })
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
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
        return "login";
    }
}

