package com.loctek.intelligent_bed_monitoring_platform.common_model.domain.permission_management.entity;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevDeviceInfoRequest;
import lombok.Data;

import java.util.List;

@Data
public class User {
    // 用户ID
    private int userID;
    // 用户手机号
    private String userPhone;
    // 用户密码
    private String password;
    // 用户名
    private String userName;
    // 设备
    private List<DevDeviceInfoRequest> deviceList;
}
