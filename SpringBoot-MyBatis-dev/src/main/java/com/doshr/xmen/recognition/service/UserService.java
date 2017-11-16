package com.doshr.xmen.recognition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doshr.xmen.recognition.mapper.UserMapper;
import com.doshr.xmen.recognition.model.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(){
        User user=userMapper.findUserInfo();
        //User user=null;
        return user;
    }

}
