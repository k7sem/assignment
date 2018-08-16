package com.k7.moment.controller;

import com.k7.moment.mapper.UrlMapper;
import com.k7.moment.mapper.UserMapper;
import com.k7.moment.pojo.User;
import com.k7.moment.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author Kevin
 * @Since 08/16/2018
 *
 */

@Controller
public class PageController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UrlMapper urlMapper;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/uploading")
    public String goUploadImg(Model model) {

        List<User> users = userMapper.getAllUsers();
        model.addAttribute("users", users);

        return "upload";
    }

    @RequestMapping(value="/userconf")
    public String goUserConf(Model model) {

        List<User> users = userMapper.getAllUsers();
        model.addAttribute("users", users);

        return "userconf";
    }

    @RequestMapping(value="/deluser", method = RequestMethod.GET)
    public String goDelUser(@RequestParam("id") String id, Model model) {
        String msg = "Delete user successfully";
        try {
            if (id != null) {
                userMapper.deleteUserById(Integer.parseInt(id));
                // After successfully delete user object, must delete all image url accordingly
                urlMapper.deleteAllUrlsByUserID(Integer.parseInt(id));
            }
        } catch (Exception e) {
            msg = "error occurred";
            e.printStackTrace();
        }
        model.addAttribute("msg", msg);
        return "info";
    }

    @RequestMapping(value="/adduser", method = RequestMethod.POST)
    public String goAddUser(@RequestParam("username") String username, Model model) {
        String msg = "Add user successfully";
        try {
            if (username != null) {
                userMapper.insertUser(username);
            }
        } catch (Exception e) {
            msg = "Error occurred";
            e.printStackTrace();
        }
        model.addAttribute("msg", msg);
        return "info";
    }


    @RequestMapping(value="/douploading", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile file, @RequestParam("uid") String uid,
                     HttpServletRequest request) {
       /* try {
            String userid = (String) request.getAttribute("uid");
            System.out.println("user id = " + userid);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println("user id = " + uid);
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        //String filePath = request.getSession().getServletContext().getRealPath("/");
        //System.out.println("File path [" + filePath + "]");
        try {
            FileUtil.uploadFile(file.getBytes(), "C:\\img\\", fileName);

            urlMapper.insertImgUrlByUserID(fileName, Integer.parseInt(uid));

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return "upload success";
    }
}
