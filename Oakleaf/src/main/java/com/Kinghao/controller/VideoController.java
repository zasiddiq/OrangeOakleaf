package com.Kinghao.controller;

import com.Kinghao.bean.Result;
import com.Kinghao.bean.User;
import com.Kinghao.bean.Video;
import com.Kinghao.mapper.VideoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;

@Api(tags = "Video Controller")
@Controller
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoMapper videoMapper;

    @PostMapping(value = "/upload")
    @ApiOperation(value="Upload a video")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blob", value = "A blob video file", required = true),
            @ApiImplicitParam(name = "duration", value = "Video duration (hh:mm:ss)"),
            @ApiImplicitParam(name = "username", value = "Video duration (hh:mm:ss)")
    })
    @ResponseBody
    public String insertVideo(@RequestParam("blob") MultipartFile video,
                              @RequestParam(value = "duration", required=false) String duration,
                              @RequestParam("username") String username) throws IOException {
        Video vid = new Video();

        byte[] videoContent = video.getBytes();
        vid.setUsername(username);
        vid.setRecordTime( new Timestamp(System.currentTimeMillis()));
        vid.setDuration(duration);
        videoMapper.addRecord(vid);
        FileOutputStream out = new FileOutputStream("/root/videos/" + vid.getId() + ".webm");
        out.write(videoContent);
        out.close();
        return "Successfully stored a video for user: " + username;
    }

    @GetMapping(value = "/getList")
    @ApiOperation(value="Get a list of recorded videos")
    @ResponseBody
    public Result getVideoList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userType = ((User)session.getAttribute("user")).getUserType();
        Result result = new Result();
        result.setSuccess(false);
        if (userType == 1) {
            Map[] resList = videoMapper.getVideoList();
            result.setDetail(resList);
            result.setSuccess(true);
            return result;
        }
        result.setMsg("Permission denied");
        return result;
    }

    @PostMapping(value = "/download")
    @ApiOperation(value="Get a single video by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "video id", required = true)
    })
    @ResponseBody
    public ResponseEntity<Object> getVideoById(HttpServletRequest request, @RequestParam("id") String id) throws FileNotFoundException {
        HttpSession session = request.getSession();
        Integer userType = ((User)session.getAttribute("user")).getUserType();

        if (userType == 1) {
            String filename = "/root/videos/" + id + ".webm";
            File file = new File(filename);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format(("attachment; filename=\"%s\""), file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            ResponseEntity<Object> responseEntity =
                    ResponseEntity.ok().headers(headers)
                    .contentLength((file.length()))
                    .contentType(MediaType.parseMediaType("application/txt")).body(resource);
            return responseEntity;
        }
        return null;
    }
}
