package com.Kinghao.service;

import com.Kinghao.bean.ReadBook;
import com.Kinghao.bean.Result;
import com.Kinghao.bean.User;
import com.Kinghao.mapper.ReadBookMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Service
public class ReadBookService {
    @Autowired
    private ReadBookMapper readBookMapper;

    public Result openBook(ReadBook readBook, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User curUser=(User)session.getAttribute("user");
        Result r =  new Result();
        r.setSuccess(false);
        Integer bookid = readBook.getBookId();
        if (bookid > curUser.getBookPointer()) {
            r.setMsg(String.format("Book %d is locked", bookid));
            return r;
        }
        readBook.setUsername(curUser.getUsername());
        readBook.setOpentime(new Timestamp(System.currentTimeMillis()));
        readBookMapper.openBook(readBook);
        r.setSuccess(true);
        r.setMsg(String.format("Start reading book with bookId %d", readBook.getBookId()));
        session.setAttribute("readBook", readBook);
        return r;
    }

    public Result closeBook(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ReadBook openedBook = (ReadBook)session.getAttribute("readBook");
        Result r = new Result();
        r.setSuccess(true);
        if (openedBook == null) {
            r.setSuccess(false);
            r.setMsg("No opened book to close");
            return r;
        }
        openedBook.setClosetime(new Timestamp(System.currentTimeMillis()));
        readBookMapper.closeBook(openedBook);
        session.removeAttribute("readBook");
        r.setMsg(String.format("Successfully closed book with bookId %d", openedBook.getBookId()));
        return r;
    }
}
