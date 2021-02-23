package com.amazon.config;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Properties {
	JSONParser parser;
	private String pomFile;
	
	public Properties() {
		pomFile = System.getProperty("user.dir")+"/Configurations/pom.json";
	}
	public String getBrowser() {
		return getData("browser");
	}
	public String getUrl() {
		return getData("url");
	}
	public String getDriver() {
		return System.getProperty("user.dir")+getData("driver-path");
	}
	public String getDriverUrl() {
		return getData("driverUrl");
	}
	private String getData(String key) {
		parser = new JSONParser();
		String value = null;
		try {
			String path = System.getProperty("user.dir")+"/Configurations/config.json";
			Object obj = parser.parse(new FileReader(path));
			JSONObject jsonObject = (JSONObject) obj;
			value = (String)jsonObject.get(key);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	public String getUiExpected(String pom, String key) {
		String value = "";
		parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(pomFile));
			JSONObject json = (JSONObject)obj;
			JSONObject json2 = (JSONObject) json.get(pom);
			value = (String)json2.get(key);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return value;
	}
}
