package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	
	static Properties prop = new Properties();
	
	static {
		try {
			prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getValue(String key) {
		if(prop==null) {
			return null;
		}
		return prop.get(key);
	}
	
}
