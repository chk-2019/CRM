package com.ck.controller;

import com.ck.Exception.LoginException;
import com.ck.domain.User;
import com.ck.service.UserService;
import com.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("name")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("login.do")
    @ResponseBody
    public Map<Object, Object> userLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, User userFromRequest) throws ServletException, IOException {
        Map<Object,Object> res = new HashMap<Object,Object>();
        System.out.println(userFromRequest.getLoginAct());
        System.out.println(userFromRequest.getLoginPwd());
        String md5loginPwd="";
        if (userFromRequest.getLoginAct()!=null){
            md5loginPwd= MD5Util.getMD5(userFromRequest.getLoginPwd());
        }
        userFromRequest.setLoginPwd(md5loginPwd);
        User user = null;
        try {
            user = userService.selectUser(userFromRequest);
            session.setAttribute("user",user);

        } catch (LoginException e) {
            e.printStackTrace();
            res.put("result",false);
            res.put("msg",e.getMessage());
            return res;
        }
//        if (null != user){
////            mv.addObject("result",true);
////            mv.addObject("msg","登录成功");
//            res.put("result",true);
//            res.put("msg","登录成功");
//            return res;
//
//
//        }
//        else{
////            mv.addObject("result",false);
////            mv.addObject("msg","登录失败");
//            res.put("result",false);
//            res.put("msg","登录失败");
//            return res;
//
//        }
        res.put("result",true);
        return res;

    }
    @RequestMapping("welcome.do")
    public String welCome(String url){
        String u = url;
        return u;
    }

}
