package com.chinasoft.usermanager.service;

import com.chinasoft.usermanager.domain.PageInfo;
import com.chinasoft.usermanager.domain.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    void addUsers(User user);

    void deleteUser(Integer id);

    User findOne(Integer id);

    void updateUser(User user);

    User login(String username, String password, UUID uuid);

    PageInfo<User> findUserByPage(Integer size, Integer current, Map<String, String[]> condition);
}

