package de.fernuni.pi3.interactionmanager;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

public final class Event {

	private static final String DEFAULT_VALUE = "Unknown";
	
	private String name;
	private String appType;
	private String appInstanceId;
	private Map<String, Object> properties;
	private Map<String, Object> customVars;

	public Event(String eventName) {
		name = eventName;
		appType = DEFAULT_VALUE;
		appInstanceId = "unknownInstance";
		properties = new LinkedHashMap<String, Object>();
		customVars = new LinkedHashMap<String, Object>();
	}

	public Event() {
		this(DEFAULT_VALUE);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppInstanceId(String appInstanceId) {
		this.appInstanceId = appInstanceId;
	}

	public String getAppInstanceId() {
		return appInstanceId;
	}

	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}

	public Object getProperty(String key) {
		if (!properties.containsKey(key)) {
			throw new RuntimeException("Event " + this
					+ " does not contain property '" + key + "' ("
					+ this.toJson() + ")");
		}
		return properties.get(key);
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setCustomVar(String key, Object value) {
		customVars.put(key, value);
	}

	public Object getCustomVar(String key) {
		return customVars.get(key);
	}

	public void setCustomVars(Map<String, Object> customVars) {
		this.customVars = customVars;
	}

	public Map<String, Object> getCustomVars() {
		return customVars;
	}

	public static Event fromJson(String eventJson) {
		Gson gson = new Gson();
		Event event = gson.fromJson(eventJson,
				Event.class);
		return event;
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public boolean isEmpty() {
		return name.equals(DEFAULT_VALUE);
	}

}
