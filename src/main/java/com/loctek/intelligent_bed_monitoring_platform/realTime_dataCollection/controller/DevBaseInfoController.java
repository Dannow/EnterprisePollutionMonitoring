package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.controller;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.DevLocation;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.service.DevLocationService;
import com.loctek.intelligent_bed_monitoring_platform.system_common.entity.Result;
import com.loctek.intelligent_bed_monitoring_platform.system_common.entity.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/realTimeDataCollection/devBaseInfo")
public class DevBaseInfoController {
    @Autowired
    DevLocationService devLocationService;

    /**
     * 获取设备位置信息
     * @param map
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getDevLoacation", method = RequestMethod.POST)
    public Result getDevLoacation(@RequestBody Map<String,Object> map) throws SQLException {
        String sn = (String) map.get("sn");
        DevLocation location = devLocationService.getLocation(sn);
        return new Result(ResultCode.SUCCESS, location);
    }
}
