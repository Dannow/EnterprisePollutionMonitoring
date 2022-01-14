package com.loctek.intelligent_bed_monitoring_platform.permission_management.service.serviceImpl;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.permission_management.entity.User;
import com.loctek.intelligent_bed_monitoring_platform.permission_management.dao.UserDao;
import com.loctek.intelligent_bed_monitoring_platform.permission_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    // 添加用户
    public void insertUser(User user){
        userDao.insertUser(user);
    }

    // 根据ID获取用户
    public User selectUserByID(int userID){
        return userDao.selectUserByID(userID);
    }

    // 用户登录
    public User checkUser(String userPhone,String password){
        return userDao.checkUser(userPhone,password);
    }

    // 获得全部用户
    public List<User> getAllUser(){
        return userDao.getAllUser();
    }
}
