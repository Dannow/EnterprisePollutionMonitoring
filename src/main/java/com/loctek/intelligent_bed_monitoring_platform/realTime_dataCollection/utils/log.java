package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class log {
	
	private static Map<String, Integer> logDebugMap = new ConcurrentHashMap<>();
	
	static boolean getDebugByClassName(String classname){
		
		boolean debug = false;
		if (logDebugMap == null || logDebugMap.isEmpty()) {
			return debug;
		}
		if (logDebugMap.get(classname)  != null && logDebugMap.get(classname) != 0) {
			debug = true;
		}
        
        return debug;
    }
	
	static public void updateDebug(ClassLog mClassLog)
	{
		  if(logDebugMap == null){
			  logDebugMap = new ConcurrentHashMap<>();
	        }
		  logDebugMap.put(mClassLog.getClassname(),mClassLog.getValue());
	}

	
	
	static public void error(String str)
	{
		System.out.println(str);
	}

	static public void info(String classname,String str)
	{
		if(getDebugByClassName(classname))
			System.out.println(str);
	}
	

	static public void debug(String classname, String str)
	{

		if(getDebugByClassName(classname))
			System.out.println(str);
	}
	
	
}
