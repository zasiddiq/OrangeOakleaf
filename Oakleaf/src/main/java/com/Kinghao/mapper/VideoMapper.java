package com.Kinghao.mapper;


import com.Kinghao.bean.Hotspot;
import com.Kinghao.bean.Video;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Lob;
import java.sql.Blob;
import java.util.Map;

@Mapper
@Repository
public interface VideoMapper {
    @Insert("insert into video values(NULL,#{username},#{recordTime},#{duration});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addRecord(Video video);

    @Select("select * from video order by username, recordTime;")
    Map[] getVideoList();

//    @Select("select content from video where id = #{id};")
//    byte[] getVideoById(@Param("id") String id);

}
