package com.ck.service;

import com.ck.Exception.LoginException;
import com.ck.dao.UserDao;
import com.ck.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public User selectUser(User user) throws LoginException;
    public int addUser(User user);
    public List<User> getUserList();
    public String getNameById(String owner);
}
