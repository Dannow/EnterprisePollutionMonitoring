package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevLocationDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevGsmGpsInfoRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.DevLocation;

public class DevGsmGpsInfoParser  extends BaseRequestParser{
	
	
	
	@Override
	public DevGsmGpsInfoRequest parse(IntelligentMattressProtocol protocol) {
		
		byte[] bytes = protocol.getContent();


		DevGsmGpsInfoRequest gsmGpsInfoRequest = new DevGsmGpsInfoRequest();

		super.parse(protocol, gsmGpsInfoRequest);
		
		float latitude = ByteUtils.byte2float(bytes, DataPackageConstants.COMMAND_CODE_INDEX+1);
		float longitude = ByteUtils.byte2float(bytes, DataPackageConstants.COMMAND_CODE_INDEX+1+4);
		
		DevLocation mLocation = new DevLocation();
		
		mLocation.setLatitude(latitude);
		mLocation.setLongitude(longitude);
		gsmGpsInfoRequest.setmLocation(mLocation);
		
		DevLocationDao dao = new DevLocationDao();
		dao.insertNewLocation(gsmGpsInfoRequest);
		
		
		
		
		
	        
	        
		// TODO Auto-generated method stub
		return gsmGpsInfoRequest;
	}



}
