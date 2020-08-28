package com.Kinghao.service;

import com.Kinghao.bean.Result;
import com.Kinghao.bean.UnlockBook;
import com.Kinghao.bean.User;
import com.Kinghao.mapper.unlockBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Service
public class UnlockService {
    @Autowired
    private unlockBookMapper unlockBookMapper;

    public String addRecord(UnlockBook unlockBook, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("user");
        String curUsername = curUser.getUsername();
        Integer bookid = unlockBook.getBookId();
        Integer curUserType = curUser.getUserType();

        if ((curUserType == 1) ||
                (curUserType == 2 && 0 <= bookid && bookid <= 2) ||
                (curUserType == 3 && 3 <= bookid && bookid <= 5)) {
            if (unlockBookMapper.checkRepeat(curUsername, bookid).length == 0) {
                Timestamp curTime = new Timestamp(System.currentTimeMillis());
                unlockBook.setUsername(curUsername);
                unlockBook.setUnlockTime(curTime);
                unlockBookMapper.addRecord(unlockBook);
                curUser.setBookPointer(bookid);
                session.setAttribute("user", curUser);
                return String.format("Book %d unlocked successfully for user: %s", bookid, curUsername);
            }
            return "Book already unlocked";
        }
        return "Invalid bookid for userType " + curUserType;
    }

    public Result checkUnlock(HttpServletRequest request) {
        HttpSession session=request.getSession();
        String username =((User)session.getAttribute("user")).getUsername();
        String[] bookids = unlockBookMapper.checkUnlock(username);
        Result r = new Result();
        r.setMsg(String.format("%s has %d unlocked books", username, bookids.length));
        r.setSuccess(true);
        r.setDetail(bookids);
        return r;
    }
}
