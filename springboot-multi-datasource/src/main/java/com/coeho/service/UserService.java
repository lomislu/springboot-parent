package com.coeho.service;

import com.coeho.mapper.UserMapper;
import com.coeho.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Lomis Lu (http://blog.coeho.com)
 * @email: lomis.lu@gmail.com
 * @datetime: 2018-02-24 18:15
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void save(User user) {
        userMapper.insertSelective(user);
    }

    @Transactional(readOnly = true)
    public User find(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
