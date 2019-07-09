package com.sporthealth.monitor;

import java.util.Map;

public interface MonitorRequest {
	String getMark();
	String getSource();
	String getParameter(String key);
	String getParameter(String key, String defaultValue);
	Map<String, String> getParameters();
}
