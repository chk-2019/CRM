package com.ck.service;

import com.ck.Exception.LoginException;
import com.ck.dao.UserDao;
import com.ck.domain.User;
import com.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;
    @Override
    public User selectUser(User user1) throws LoginException {
        User user = userDao.queryUser(user1);
        //验证用户名密码
        if (user==null){
            throw new LoginException("用户名密码不正确");
        }
        String expireTime = user.getExpireTime();
        String sysTime = DateTimeUtil.getSysTime();
        //验证用户账号有没有失效
        if(expireTime.compareTo(sysTime)<0){
            throw new LoginException("账号已失效");
        }
        //验证用户锁定状态
        String lockState = user.getLockState();
        if ("0".equals(lockState)){
            throw new LoginException("账号锁定，请联系管理员");
        }
        return user;
    }

    @Override
    public int addUser(User user) {
        int rowNum = userDao.insertUser(user);
        return rowNum;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        return userList;
    }

    @Override
    public String getNameById(String owner) {
        String nameById = userDao.getNameById(owner);
        return nameById;
    }
}
