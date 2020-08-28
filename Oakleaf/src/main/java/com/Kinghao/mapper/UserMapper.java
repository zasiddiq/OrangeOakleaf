package com.Kinghao.mapper;

import com.Kinghao.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * mapper
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * @param username
     * @return
     */
    @Select(value = "select u.username,u.password from user u where u.username=#{username}")
    @Results
            ({@Result(property = "username",column = "username"),
              @Result(property = "password",column = "password")})
    User findUserByName(@Param("username") String username);

    /**
     * Register  add one user record
     * @param user
     * @return
     */
    @Insert("insert into user values(NULL,#{username},#{password},#{userType},#{bookPointer})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void regist(User user);

    /**
     * Login
     * @param user
     * @return
     */
    @Select("select * from user u where u.username = #{username}")
    User login(User user);
}
