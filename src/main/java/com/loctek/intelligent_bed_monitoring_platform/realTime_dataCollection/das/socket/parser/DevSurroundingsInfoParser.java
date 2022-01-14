package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevSurroundingsInfoRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;

public class DevSurroundingsInfoParser  extends BaseRequestParser{

	@Override
	public DevSurroundingsInfoRequest parse(IntelligentMattressProtocol protocol) {
		// TODO Auto-generated method stub
		byte[] bytes = protocol.getContent();

//		log.debug("---DevSurroundingsInfoRequest parse byte len = "
//				+ bytes.length);
		DevSurroundingsInfoRequest surroundingsInfoRequest = new DevSurroundingsInfoRequest();

		super.parse(protocol, surroundingsInfoRequest);
		
		
		
		surroundingsInfoRequest.setTemp(ByteUtils.byte2float(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 1));
		
		short dampness = (short)(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 5] & 0xff);
		surroundingsInfoRequest.setDampness(dampness);
		

		short lum = (short)(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 6] & 0xff);
		surroundingsInfoRequest.setLum(lum);
		

		short noise = (short)(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 7] & 0xff);
		surroundingsInfoRequest.setNoise(noise);
		
		
		
		
		
		return surroundingsInfoRequest;
	}

}
