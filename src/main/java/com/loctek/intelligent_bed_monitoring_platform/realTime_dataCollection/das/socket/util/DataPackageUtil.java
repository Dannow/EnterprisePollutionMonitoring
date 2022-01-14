package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DataPackageUtil
{

    public static List<byte[]> splitPackage(byte[] source){
        List<byte[]> bytesList = new ArrayList<byte[]>();
        //根据固定的头部消息和长度，将消息分包
        int end = source.length / 2;
        for(int i=0;i<end;i++) {
            if ((source[i] & 0xFF) == 0xA5 && (source[i+1] & 0xFF) == 0x5A) {
                //消息固定的头部
                int length = (source[i+12]<<8) + source[i+13];
                //固定的头部长度 + 参数长度 + BBCcheck
                byte[] resultBytes = new byte[14 + length + 1];
                if (source.length < i + 14 + length) {
                    log.error("报错了...");
                }
                System.arraycopy(source, i, resultBytes, 0, 14 + length);
                bytesList.add(resultBytes);
                i = i + 14 + length + 1;
            }
        }
        return bytesList;
    }
    
    public static void main(String[] args)
    {
        byte[] bytes = new byte[] {34, 34, 34, 34, 34, 34, 34, 34, 34, -91, 90, 48, 49, 48, 48, 48, 49, 48, 48, 48, 56, 0, 3, 2, 0, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        List<byte[]> result = splitPackage(bytes);
        byte[] snBytes = new byte[] {48, 49, 48, 48, 48, 49, 48, 48, 48, 56};
        
        String sn = new String(snBytes);
        System.out.println(sn);
        
    }
}
