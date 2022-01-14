package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.BaseRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocketResponse
{
//    public SocketResponse(BaseRequest request,int commandCode,byte[] commandParams)
//    {
//        this.request = request;
//        this.commandCode = commandCode;
//        this.commandParams = commandParams;
//    }
////    
//    public SocketResponse(BaseRequest request)
//    {
//    	
//    }
    		
    
    private int header0 = 0xa5;

    private int header1 = 0x5a;

    protected BaseRequest request;
    protected String sn;

    protected int commandCode;

    protected byte[] commandParams;

    public byte[] getResponse() {
        int length = commandParams.length + 1;
        
        byte[] result = new byte[14 + length + 1];
        result[0] = (byte)this.header0;
        result[1] = (byte)this.header1;
        System.arraycopy(request.getSn().getBytes(), 0, result, 2, 10);
        
        int H = length / 256;
        int L = length % 256;
        result[12] = (byte)H;
        result[13] = (byte)L;
        
        // insert command code and param
        result[14] = (byte) commandCode;
        if (commandParams.length > 0) {
            System.arraycopy(commandParams, 0, result, 14 + 1, commandParams.length);
        }
        
        // insert BBCcheck
        byte bbcCheck = ByteUtils.getXor(result, 2);
        result[result.length - 1] = bbcCheck;
        return result;
    }
    
    public static void main(String[] args)
    {
        int length = 9;
        int H = length / 256;
        int L = length % 256;
        System.out.println(H);
        System.out.println(L);
    }
}
