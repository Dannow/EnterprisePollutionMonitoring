package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class LinuxCalProcess
{
    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)Native.loadLibrary("cal_process",CLibrary.class);
        int api_Cal_data(int key,int data);
        int api_setPath(String path);
        int api_getType();
        int api_getVersion();
        char api_getBBCcheck();
    }
    
    
    public static void main(String[] args)
    {
//        int i = CLibrary.INSTANCE.Cal_data((short)5,(byte)2);
//        System.out.println(i);
    }
}
