package com.k7.moment.controller;

import com.k7.moment.mapper.UrlMapper;
import com.k7.moment.mapper.UserMapper;
import com.k7.moment.pojo.Url;
import com.k7.moment.pojo.User;
import com.k7.moment.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @Author Kevin
 * @Since 08/16/2018
 *
 */

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UrlMapper urlMapper;

    @RequestMapping("/getusers")
    public List<User> getUsers() {
        List<User> users = userMapper.getAllUsers();
        return users;
    }

    @RequestMapping("/getimg")
    public List<Url> geturls(@RequestParam("uid") int uid) {
        List<Url> urls = urlMapper.getAllUrlByUserID(uid);
        return urls;
    }


}

