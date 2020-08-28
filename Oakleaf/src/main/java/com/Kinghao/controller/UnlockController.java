package com.Kinghao.controller;

import com.Kinghao.bean.*;
import com.Kinghao.mapper.unlockBookMapper;
import com.Kinghao.service.UnlockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    private UnlockService unlockService;

    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @PostMapping(value = "/addUnlock")
    @ApiOperation(value="new unlock")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookId",value = "New Unlocked bookId",required = true),
    })
    @ResponseBody
    public String addRecord(UnlockBook unlockBook, HttpServletRequest request) {
        return unlockService.addRecord(unlockBook, request);
    }

    @GetMapping(value="/unlockBooks")
    @ApiOperation(value="check unlocked books of current user")
    @ResponseBody
    public Result checkUnlock(HttpServletRequest request) {
        return unlockService.checkUnlock(request);
    }
}
