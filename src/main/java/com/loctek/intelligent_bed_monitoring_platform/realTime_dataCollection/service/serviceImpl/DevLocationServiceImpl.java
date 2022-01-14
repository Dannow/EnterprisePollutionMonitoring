package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.service.serviceImpl;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevLocationDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.DevLocation;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.service.DevLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DevLocationServiceImpl implements DevLocationService {
    @Autowired
    DevLocationDao devLocationDao;

    // 获取设备信息
    public DevLocation getLocation(String sn) throws SQLException {
        return devLocationDao.getLocation(sn);
    }
}
