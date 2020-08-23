package com.Kinghao.controller;

import com.Kinghao.bean.Hotspot;
import com.Kinghao.bean.ReadBook;
import com.Kinghao.bean.Result;
import com.Kinghao.bean.User;
import com.Kinghao.service.HotspotService;
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

@Api(tags = "Hotspot Controller")
@Controller
@RequestMapping("/hotspot")
public class HotspotController {

    @Autowired
    private HotspotService hotspotService;
    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @PostMapping("/addRecord")
    @ApiOperation("Add the clicking event of the hotspot")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hotspotName",value = "Name of the hotspot",required = true),
            @ApiImplicitParam(name = "hotspotType",value = "Type: quotes, vocabulary, multiple_choice, open_ended_discussion, replay_button, next_button, back_button",required = true),
            @ApiImplicitParam(name = "clickResult",value = "Record the answer(if there is one)"),
            @ApiImplicitParam(name = "remarks",value = "Extra remark of the clicking event(if there is one)"),
    })
    @ResponseBody
    public Result addRecord(Hotspot hotspot, HttpServletRequest request){
        HttpSession session=request.getSession();
        User curUser=(User)session.getAttribute("user");
        ReadBook curBook = (ReadBook)session.getAttribute("readBook");
        String curUsername=curUser.getUsername();
        Timestamp curTime=new Timestamp(System.currentTimeMillis());
        hotspot.setUsername(curUsername);
        hotspot.setClickTime(curTime);
        hotspot.setReadBookId(curBook.getId());
        return hotspotService.addRecord(hotspot);
    }
}
