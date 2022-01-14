package com.loctek.intelligent_bed_monitoring_platform;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.permission_management.entity.User;
import com.loctek.intelligent_bed_monitoring_platform.permission_management.service.UserService;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevBodyDataDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevLocationDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class IntelligentBedMonitoringPlatformApplicationTests {
    @Autowired
    UserService userService;

    @Test
    void contextLoads() throws SQLException, ParseException {
//        DevBodyDataDao devBodyDataDao = new DevBodyDataDao();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        String time = "2021-07-28";
//        Date date = ft.parse(time);
//        System.out.println(devBodyDataDao.getBodySignalByTime("0100500125",date));
////        System.out.println(devBodyDataDao.getBodySignalByNumber("0100500125",2));
        System.out.println(userService.selectUserByID(2));
    }

}
