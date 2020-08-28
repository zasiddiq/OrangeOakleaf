package com.Kinghao.controller;


import com.Kinghao.bean.ReadBook;
import com.Kinghao.bean.User;
import com.Kinghao.mapper.ReadBookMapper;
import com.Kinghao.service.ReadBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.Kinghao.bean.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Api(tags = "Read a book")
@Controller
@RequestMapping("/read")
public class ReadController {
    @Autowired
    private ReadBookService readBookService;
    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @GetMapping(value = "/openBook")
    @ApiOperation(value="new readBook record")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookId",value = "Open book id",required = true),
    })
    @ResponseBody
    public Result addRecord(ReadBook readBook, HttpServletRequest request) {
        logger.trace("open book was requested");
        readBookService.closeBook(request);
        return readBookService.openBook(readBook, request);
    }

    @GetMapping(value = "/closeBook")
    @ApiOperation(value="close a book")
    @ResponseBody
    public Result updateRecord(HttpServletRequest request) {
        logger.trace("close book was requested");
        return readBookService.closeBook(request);
    }
}
