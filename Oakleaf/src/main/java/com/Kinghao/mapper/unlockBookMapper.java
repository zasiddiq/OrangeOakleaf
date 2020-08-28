package com.Kinghao.mapper;

import com.Kinghao.bean.UnlockBook;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author Kinghao
 * @Date 2020/8/17 16:54
 * @Version 1.0
 */

@Mapper
@Repository
public interface unlockBookMapper {
    @Update("update user set bookPointer=#{bookId} where username=#{username};"+
            "insert into unlockBook values(#{id},#{username},#{bookId},#{unlockTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addRecord(UnlockBook unlockBook);

    @Select("select bookid from unlockBook where username=#{username};")
    String[] checkUnlock(@Param("username") String username);

    @Select("select bookid from unlockBook where username=#{username} and bookid=#{bookid};")
    String[] checkRepeat(@Param("username") String username,
                         @Param("bookid") Integer bookid);
}


