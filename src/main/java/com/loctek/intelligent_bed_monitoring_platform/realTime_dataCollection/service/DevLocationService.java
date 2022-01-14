package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.service;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.DevLocation;

import java.sql.SQLException;

public interface DevLocationService {
    // 获取设备信息
    public DevLocation getLocation(String sn) throws SQLException;
}
