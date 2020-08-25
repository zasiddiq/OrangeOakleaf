package com.Kinghao.mapper;


import com.Kinghao.bean.ReadBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReadBookMapper {
    @Insert("insert into readBook(username, bookid, opentime) values(#{username},#{bookId},#{opentime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "readBook.id")
    void openBook(ReadBook rb);

    @Update("update readBook set closetime=#{closetime} where id=#{id}")
    void closeBook(ReadBook rb);
}
