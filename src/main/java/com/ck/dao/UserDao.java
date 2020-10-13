package com.ck.dao;

import com.ck.domain.User;

import java.util.List;

public interface UserDao {
    public User queryUser(User user);
    public int insertUser(User user);
    public List<User> getUserList();
    public String getNameById(String owner);
}
