package de.fernuni.pi3.interactionmanager;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class InteractionManagerEventTest {

	private static final String EVENT_NAME = "testevent";
	private static final String VAR_VALUE = "testVarValue";
	private static final String PROP_VALUE = "testPropValue";
	private static final String VAR_KEY = "testVarKey";
	private static final String PROP_KEY = "testPropKey";
	private static final String APP_INSTANCE_ID = "appInstanceId";
	private static final String APP_TYPE = InteractionManagerEventTest.class
			.getName();
	private final static String TEST_EVENT_JSON = "{\"name\":\"testevent\","
			+ "\"appType\":\"de.fernuni.pi3.interactionmanager.InteractionManagerEventTest\","
			+ "\"appInstanceId\":\"appInstanceId\","
			+ "\"properties\":{\"testPropKey\":\"testPropValue\",\"varKeyMap\":{\"varSubKeyMap\":\"varSubValueMap\"}},"
			+ "\"customVars\":{\"testVarKey\":\"testVarValue\",\"varKeyMap\":{\"varSubKeyMap\":\"varSubValueMap\"}}}";

	@Test
	public void testToJson() throws Exception {

		Event event = new Event(EVENT_NAME);

		event.setAppInstanceId(APP_INSTANCE_ID);
		event.setAppType(APP_TYPE);
		event.setProperty(PROP_KEY, PROP_VALUE);
		event.setCustomVar(VAR_KEY, VAR_VALUE);

		HashMap<String, String> customVarMap = new HashMap<String, String>();
		customVarMap.put("varSubKeyMap", "varSubValueMap");
		event.setCustomVar("varKeyMap", customVarMap);
		event.setProperty("varKeyMap", customVarMap);
		System.out.println(event.toJson());
		assertEquals("Unexpected JSON string", TEST_EVENT_JSON, event.toJson());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testFromJson() throws Exception {

		Event event = Event
				.fromJson(TEST_EVENT_JSON);
		Map<String, String> customVarMap = (Map<String, String>) event
				.getCustomVar("varKeyMap");
		Map<String, String> propertiesMap = (Map<String, String>) event
				.getCustomVar("varKeyMap");

		assertEquals("varSubValueMap", customVarMap.get("varSubKeyMap"));
		assertEquals("varSubValueMap", propertiesMap.get("varSubKeyMap"));
		assertEquals(APP_INSTANCE_ID, event.getAppInstanceId());
		assertEquals(APP_TYPE, event.getAppType());
		assertEquals(PROP_VALUE, event.getProperty(PROP_KEY));
		assertEquals(VAR_VALUE, event.getCustomVar(VAR_KEY));

	}

}
