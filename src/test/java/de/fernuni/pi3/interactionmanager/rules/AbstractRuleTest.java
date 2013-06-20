package de.fernuni.pi3.interactionmanager.rules;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public abstract class AbstractRuleTest {

	private static final String ID_STRING = "__TESTID__";

	private class RuleTestData {
		Event givenEvent;
		InstanceVars givenInstanceVars;
		Event expectedEvent;
		InstanceVars expectedInstanceVars;
	}

	List<RuleTestData> testData = new ArrayList<AbstractRuleTest.RuleTestData>();

	@Before
	abstract public void setUpTestData();

	protected void addTestData(Event givenEvent,
			InstanceVars givenInstanceVars, Event expectedEvent,
			InstanceVars expectedInstanceVars) {
		RuleTestData data = new RuleTestData();
		data.givenEvent = givenEvent;
		data.givenInstanceVars = givenInstanceVars;
		if (expectedEvent == null) {
			data.expectedEvent = new Event();
		} else {
			data.expectedEvent = expectedEvent;
		}
		data.expectedInstanceVars = expectedInstanceVars;

		testData.add(data);
	}

	@Test
	public void testRule() throws Exception {

		for (RuleTestData data : testData) {

			Event outEvent = new Event();
			getRule().processEvent(data.givenEvent, outEvent,
					data.givenInstanceVars);

			// assert outgoing event
			if (data.expectedEvent.isEmpty()) {
				Assert.assertTrue("No outgoing event expected",
						outEvent.isEmpty());
			} else {
				Assert.assertEquals(
						"Unexpected output event",
						data.expectedEvent.toJson().replace(ID_STRING,
								outEvent.getId()), outEvent.toJson());
			}

			// assert instance vars
			Assert.assertEquals("Unexpected instance vars",
					data.expectedInstanceVars, data.givenInstanceVars);
		}
	}

	protected static Event createTestEvent() {
		String json = "{\"id\":\"" + ID_STRING + "\",\"name\":\"Unknown\",\"appType\":\"Unknown\",\"appInstanceId\":\"unknownInstance\",\"properties\":{},\"customVars\":{}}";
		return Event.fromJson(json);
	}

	abstract protected Rule getRule();

}
