package com.Kinghao.service;

import com.Kinghao.bean.Result;
import com.Kinghao.bean.User;
import com.Kinghao.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    /**
     * Register
     * @param user
     * @return Result
     */
    public Result regist(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User existUser = userMapper.findUserByName(user.getUsername());
            if(existUser != null){
                //username has existed
                result.setMsg("The user name has been used.");
                logger.trace("For name:"+existUser.getUsername()+";"+result.getMsg());
            }else{
                PasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
                user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
                userMapper.regist(user);
                //System.out.println(user.getId());
                result.setMsg("Register successfully.");
                logger.trace("For name:"+user.getUsername()+";"+result.getMsg());
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Login
     * @param user
     * @return Result
     */
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User fullUserInfo=userMapper.login(user);
            Boolean passMatch = new StrongPasswordEncryptor().checkPassword(user.getPassword(), fullUserInfo.getPassword());
            if(!passMatch){
                result.setMsg("Wrong username or password.");
                logger.trace(user.getUsername()+" login failed: "+result.getMsg());
            }else{
                result.setMsg("Login successfully");
                logger.trace(user.getUsername()+" login success: "+result.getMsg());
                result.setSuccess(true);
                user.setId(fullUserInfo.getId());
                user.setUserType(fullUserInfo.getUserType());
                user.setBookPointer(fullUserInfo.getBookPointer());
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
