package com.loctek.intelligent_bed_monitoring_platform.permission_management.controller;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.permission_management.entity.User;
import com.loctek.intelligent_bed_monitoring_platform.permission_management.service.UserService;
import com.loctek.intelligent_bed_monitoring_platform.system_common.entity.Result;
import com.loctek.intelligent_bed_monitoring_platform.system_common.entity.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permissionManagement")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 获取用户
     * @param userID
     * @return
     */
    @RequestMapping(value = "/user/getUser/{userID}", method = RequestMethod.GET)
    public Result getUser(@PathVariable(value = "userID") String userID){
        User user = userService.selectUserByID(Integer.parseInt(userID));
        return new Result(ResultCode.SUCCESS, user);
    }


    /**
     * 获得所有用户
     * @return
     */
    @RequestMapping(value = "/user/allUser", method = RequestMethod.GET)
    public Result getAllUser(){
        List<User> allUser = userService.getAllUser();
        return new Result(ResultCode.SUCCESS, allUser);
    }
}
