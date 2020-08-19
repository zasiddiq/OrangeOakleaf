package com.Kinghao.mapper;

import com.Kinghao.bean.UnlockBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @Author Kinghao
 * @Date 2020/8/17 16:54
 * @Version 1.0
 */

@Mapper
@Repository
public interface unlockBookMapper {
    @Insert("insert into unlockBook values(#{id},#{username},#{bookId},#{unlockTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addRecord(UnlockBook unlockBook);
}
