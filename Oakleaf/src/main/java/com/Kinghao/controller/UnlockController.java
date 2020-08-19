package com.Kinghao.controller;

import com.Kinghao.bean.Hotspot;
import com.Kinghao.bean.Result;
import com.Kinghao.bean.UnlockBook;
import com.Kinghao.bean.User;
import com.Kinghao.mapper.unlockBookMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * @Author Kinghao
 * @Date 2020/8/17 16:56
 * @Version 1.0
 */

@Api(tags = "Unlock a new book")
@Controller
@RequestMapping("/unlock")
public class UnlockController {
    @Autowired
    private unlockBookMapper unlockBookMapper;

    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @PostMapping(value = "/addUnlock")
    @ApiOperation(value="new unlock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookId",value = "New Unlocked bookId",required = true),
    })
    @ResponseBody
    public void addRecord(UnlockBook unlockBook, HttpServletRequest request){
        HttpSession session=request.getSession();
        User curUser=(User)session.getAttribute("user");
        String curUsername=curUser.getUsername();
        Timestamp curTime=new Timestamp(System.currentTimeMillis());
        unlockBook.setUsername(curUsername);
        unlockBook.setUnlockTime(curTime);
        unlockBookMapper.addRecord(unlockBook);
    }
}
