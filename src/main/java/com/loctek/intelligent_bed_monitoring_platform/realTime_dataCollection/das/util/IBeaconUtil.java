package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util;

public class IBeaconUtil
{

    public static double calculateAccuracy(int measuredPower, double rssi)
    {
        if (rssi == 0)
        {
            return -1.0;
        }
        if (measuredPower == 0)
        {
            return -1.0;
        }
        if (rssi > 0)
        {
            rssi = rssi - 128;
        }
        double ratio = rssi * 1.0 / measuredPower;
        if (ratio < 1.0)
        {
            return Math.pow(ratio, 10);
        }
        else
        {
            double accuracy = (0.42093) * Math.pow(ratio, 6.9476) + 0.54992;
            return accuracy;
        }
    }
}
