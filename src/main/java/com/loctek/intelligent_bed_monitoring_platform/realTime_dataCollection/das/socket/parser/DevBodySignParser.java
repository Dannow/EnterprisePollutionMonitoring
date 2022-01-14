package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevBodyDataDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.dll.CalProcessProxy;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevBodySignRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 解析体征数据的指令，并存到数据库中
 */
public class DevBodySignParser extends BaseRequestParser
{
	private static String classname = DevBodySignParser.class.getSimpleName();

    @Override
    public DevBodySignRequest parse(IntelligentMattressProtocol protocol)
    {
        byte[] bytes = protocol.getContent();
        log.debug(classname,"---DevBodySignRequest parse byte len = " + bytes.length);
        DevBodySignRequest bodySignRequest = new DevBodySignRequest();
        
        super.parse(protocol, bodySignRequest);
        
        
        log.debug(classname,"------------------------------------");
        log.debug(classname,"------------------------------------");
        
        bodySignRequest.setBed(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 11]);
        bodySignRequest.setBreathRate(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 13]);
        bodySignRequest.setHeartRate(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 12]);
        
     
        
        log.debug(classname,"DevBodySignRequest parse 3");
        String heartRateCalStr = ByteUtils.bytesToHexString(bytes,DataPackageConstants.COMMAND_CODE_INDEX + 14,2);
        log.debug(classname,"DevBodySignRequest parse 4");
        bodySignRequest.setHeartRateCal(Short.parseShort(heartRateCalStr, 16));
        bodySignRequest.setMotion(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 16]);
        
        
        log.debug(classname,"DevBodySignRequest parse 5");
        
     
        log.debug(classname,"------------------------------------");
   
        
        

//        log.error("getBreathRate="+(bodySignRequest.getBreathRate()&0xff));
//        log.error("getHeartRate="+(bodySignRequest.getHeartRate()&0xff));
//        log.error("getHeartRateCal="+(bodySignRequest.getHeartRateCal() & 0xffff));
        
        int calValue = CalProcessProxy.calData(bodySignRequest.getHeartRateCal(), (byte)bodySignRequest.getHeartRate() );
        


        
        log.debug(classname,"DevBodySignRequest parse 6");
        bodySignRequest.setHeartRate((byte)calValue);
//        
//        log.error("getBreathRate="+(bodySignRequest.getBreathRate()&0xff));
//        log.error("getHeartRate="+(bodySignRequest.getHeartRate()&0xff));
//        log.error("getHeartRateCal="+(bodySignRequest.getHeartRateCal() & 0xffff));
        
        
        
        

//        log.debug(classname,"getBreathRate="+(bodySignRequest.getBreathRate()&0xff));
//        log.debug(classname,"getHeartRate="+(bodySignRequest.getHeartRate()&0xff));
//        log.debug(classname,"getHeartRateCal="+(bodySignRequest.getHeartRateCal() & 0xffff));
        
        
        
//        byte[] numberBytes = new byte[2];
//        System.arraycopy(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 1, numberBytes, 0, 2);
        String hexNumber = ByteUtils.bytesToHexString(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 1, 2);
        bodySignRequest.setNumber(Integer.parseInt(hexNumber,16));
        
        String hexTime = ByteUtils.bytesToHexString(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 3, 8);
        bodySignRequest.setTime(Long.parseLong(hexTime,16));



//        log.error("DevBodySignRequest "+ protocol.getSn());
        
        // 把特征数据保存到数据库
        DevBodyDataDao dao = new DevBodyDataDao();
        try {
			dao.addBodySign(bodySignRequest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return bodySignRequest;
    }

}
