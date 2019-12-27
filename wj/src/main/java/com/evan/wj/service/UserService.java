package com.evan.wj.service;

import com.evan.wj.dao.UserDAO;
import com.evan.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null != user;
    }

    public User getByName(String name) {
        return userDAO.findByUsername(name);
    }

    public User get(String name, String password) {
        return userDAO.getByUsernameAndPassword(name, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }

}
