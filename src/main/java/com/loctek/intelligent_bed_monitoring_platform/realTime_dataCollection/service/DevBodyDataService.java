package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.service;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.realTime_dataCollection.entity.BodySignal;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DevBodyDataService {
    // 根据时间获取特征数据
    public List<BodySignal> getBodySignalByTime(String sn,Date time) throws SQLException;
    // 根据数量获取体征数据
    public List<BodySignal> getBodySignalByNumber(String sn, int number) throws SQLException;
}
