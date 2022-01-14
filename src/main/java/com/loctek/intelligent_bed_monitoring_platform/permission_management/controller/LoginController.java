package com.loctek.intelligent_bed_monitoring_platform.permission_management.controller;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.permission_management.entity.User;
import com.loctek.intelligent_bed_monitoring_platform.permission_management.service.UserService;
import com.loctek.intelligent_bed_monitoring_platform.system_common.entity.Result;
import com.loctek.intelligent_bed_monitoring_platform.system_common.entity.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/permissionManagement")
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user){
        userService.insertUser(user);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,Object> map){
        String userPhone = (String) map.get("userPhone");
        String password = (String) map.get("password");
        User user = userService.checkUser(userPhone, password);
        if (user == null){
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
        return new Result(ResultCode.SUCCESS, user);
    }


}
