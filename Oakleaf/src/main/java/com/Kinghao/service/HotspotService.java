package com.Kinghao.service;


import com.Kinghao.bean.Hotspot;
import com.Kinghao.bean.Result;
import com.Kinghao.bean.User;
import com.Kinghao.mapper.HotspotMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotspotService {

    @Autowired
    private HotspotMapper hotspotMapper;
    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public Result addRecord(Hotspot hotspot){
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            hotspotMapper.addRecord(hotspot);
            result.setMsg("Add the record successfully.");
            logger.trace(hotspot.getUsername()+" clicked "+hotspot.getHotspotName());
            result.setSuccess(true);
            result.setDetail(hotspot);

        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
