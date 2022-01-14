package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.service.serviceImpl;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.realTime_dataCollection.entity.BodySignal;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevBodyDataDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.service.DevBodyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class DevBodyDataServiceImpl implements DevBodyDataService {
    @Autowired
    DevBodyDataDao devBodyDataDao;

    // 根据时间获取特征数据
    public List<BodySignal> getBodySignalByTime(String sn,Date time) throws SQLException {
        return devBodyDataDao.getBodySignalByTime(sn, time);
    }
    // 根据数量获取体征数据
    public List<BodySignal> getBodySignalByNumber(String sn, int number) throws SQLException {
        return devBodyDataDao.getBodySignalByNumber(sn,number);
    }
}
