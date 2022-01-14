package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.controller;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.realTime_dataCollection.entity.BodySignal;
import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.realTime_dataCollection.response.BodySignalResult;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.service.DevBodyDataService;
import com.loctek.intelligent_bed_monitoring_platform.system_common.entity.Result;
import com.loctek.intelligent_bed_monitoring_platform.system_common.entity.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/realTimeDataCollection/devCollectionData")
public class DevCollectionDataController {
    @Autowired
    DevBodyDataService devBodyDataService;

    @RequestMapping(value = "/getBodyData", method = RequestMethod.POST)
    public Result getBodyData(@RequestBody Map<String,Object> map) throws SQLException {
        // 获取特征类型
        String BodyType = (String) map.get("BodyType");
        // 获取时间
        String time = (String) map.get("time");
        // 获取sn
        String sn = (String) map.get("sn");
        List<BodySignalResult> BodySignalList = new ArrayList<BodySignalResult>();
        // 判断特征类型
        switch (BodyType){
            case "HeartRate":
                // 判断时间是否为空，则根据日期选择数据
                if (time == null || time.equals("")){
                    List<BodySignal> bodySignalByNumber = devBodyDataService.getBodySignalByNumber(sn, 12);
                    for (int i = bodySignalByNumber.size()-1; i > 0; i--){
                        BodySignal bodySignal = bodySignalByNumber.get(i);
                        BodySignalList.add(new BodySignalResult(bodySignal.getHeartRate(),bodySignal.getTime()));
                    }
//                    for (BodySignal bodySignal : bodySignalByNumber) {
//                        BodySignalList.add(new BodySignalResult(bodySignal.getHeartRate(),bodySignal.getTime()));
//                    }
                    return new Result(ResultCode.SUCCESS,BodySignalList);
                }else {// 否则返回12个数据
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = ft.parse(time);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    List<BodySignal> bodySignalByTime = devBodyDataService.getBodySignalByTime(sn, date);
                    for (int i = bodySignalByTime.size()-1; i > 0; i--){
                        BodySignal bodySignal = bodySignalByTime.get(i);
                        BodySignalList.add(new BodySignalResult(bodySignal.getHeartRate(),bodySignal.getTime()));
                    }
                    return new Result(ResultCode.SUCCESS,BodySignalList);
                }
            case "BreathRate":
                // 判断时间是否为空，则根据日期选择数据
                if (time == null  || time.equals("")){
                    List<BodySignal> bodySignalByNumber = devBodyDataService.getBodySignalByNumber(sn, 12);
                    for (int i = bodySignalByNumber.size()-1; i > 0; i--){
                        BodySignal bodySignal = bodySignalByNumber.get(i);
                        BodySignalList.add(new BodySignalResult(bodySignal.getBreathRate(),bodySignal.getTime()));
                    }
//                    for (BodySignal bodySignal : bodySignalByNumber) {
//                        BodySignalList.add(new BodySignalResult(bodySignal.getBreathRate(),bodySignal.getTime()));
//                    }
                    return new Result(ResultCode.SUCCESS,BodySignalList);
                }else {// 否则返回12个数据
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = ft.parse(time);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    List<BodySignal> bodySignalByTime = devBodyDataService.getBodySignalByTime(sn, date);
                    for (int i = bodySignalByTime.size()-1; i > 0; i--){
                        BodySignal bodySignal = bodySignalByTime.get(i);
                        BodySignalList.add(new BodySignalResult(bodySignal.getBreathRate(),bodySignal.getTime()));
                    }
//                    for (BodySignal bodySignal : bodySignalByTime) {
//                        BodySignalList.add(new BodySignalResult(bodySignal.getBreathRate(),bodySignal.getTime()));
//                    }
                    return new Result(ResultCode.SUCCESS,BodySignalList);
                }
            default:
                return new Result(ResultCode.FAIL);
        }
    }
}
