package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 算法库调用类
 * CalProcess
 * 
 * xieyonggao
 * xieyonggao
 * 2018年6月6日 上午9:35:59
 * 
 * @version 1.0.0
 *
 */
public class CalProcess
{
    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary("CalProcess",CLibrary.class);
        int api_Cal_data(int key,int data);
        int api_setPath(String path);
        int api_getType();
        int api_getVersion();
        char api_getBBCcheck();
    }
}
