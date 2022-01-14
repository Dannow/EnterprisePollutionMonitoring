package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.dll;

import com.sun.jna.Platform;

public class CalProcessProxy
{
    public static int calData(short key,byte data){
        if (Platform.isWindows()) {
            return CalProcess.CLibrary.INSTANCE.api_Cal_data(key, data);
        }
        return (int)LinuxCalProcess.CLibrary.INSTANCE.api_Cal_data(key, data);
    }
    
    public static int setPath(String path){
    	 if (Platform.isWindows()) {
             return CalProcess.CLibrary.INSTANCE.api_setPath(path);
         }
         return (int)LinuxCalProcess.CLibrary.INSTANCE.api_setPath(path);
 
      
    }
    
    public static int getVersion()
    {
    	 if (Platform.isWindows()) {
             return CalProcess.CLibrary.INSTANCE.api_getVersion();
         }
         return (int)LinuxCalProcess.CLibrary.INSTANCE.api_getVersion();
    }
    
    public static char getBBCcheck()
    {
    	 if (Platform.isWindows()) {
             return CalProcess.CLibrary.INSTANCE.api_getBBCcheck();
         }
         return LinuxCalProcess.CLibrary.INSTANCE.api_getBBCcheck();
         
         
    }
    
    public static int getType()
    {
    	 if (Platform.isWindows()) {
             return CalProcess.CLibrary.INSTANCE.api_getType();
         }
         return LinuxCalProcess.CLibrary.INSTANCE.api_getType();
         
    }
    
    
    public static void main(String[] args)
    {
        int result = calData((byte)1,(byte) 1);
        System.out.println(result);
        
        
      

        short heartRate_cal = 0xde;
        heartRate_cal &= 0xffff;
        short heartRate = 0xf5;
        heartRate &= 0xff;
        result = calData((short)heartRate_cal,(byte) heartRate);
        System.out.println("result:  "  + result);
        
        
    	//String path = "D:\\cocci\\update_bin\\APP1-test1.bin";
        String path = "./APP1.bin";
    	setPath(path);
    	System.out.println("version = "+getVersion());
    	System.out.println("type = "+getType());
    	
    	
    	
    	
    }

}
