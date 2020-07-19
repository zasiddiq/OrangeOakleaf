package com.Kinghao.mapper;


import com.Kinghao.bean.Hotspot;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
//@Repository
public interface HotspotMapper {
    @Insert("insert into hotspot values(#{id},#{hotspotName},#{hotspotType},#{username},#{clickTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addRecord(Hotspot hotspot);
}
