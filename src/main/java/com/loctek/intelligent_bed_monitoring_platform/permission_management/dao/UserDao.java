package com.loctek.intelligent_bed_monitoring_platform.permission_management.dao;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.permission_management.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    // 添加用户
    public void insertUser(User user);
    // 根据ID获取用户
    public User selectUserByID(int userID);
    // 用户登录
    public User checkUser(@Param("userPhone") String userPhone, @Param("password") String password);
    // 获得全部用户
    public List<User> getAllUser();
}
