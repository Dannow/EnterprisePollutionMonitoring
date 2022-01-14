package com.loctek.intelligent_bed_monitoring_platform.permission_management.service;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.permission_management.entity.User;

import java.util.List;

public interface UserService {
    // 添加用户
    public void insertUser(User user);
    // 根据ID获取用户
    public User selectUserByID(int userID);
    // 用户登录
    public User checkUser(String userPhone,String password);
    // 获得全部用户
    public List<User> getAllUser();
}
