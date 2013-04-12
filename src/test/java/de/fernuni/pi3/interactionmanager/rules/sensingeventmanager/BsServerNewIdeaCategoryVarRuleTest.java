package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsServerNewIdeaCategoryVarRuleTest extends AbstractRuleTest {

	// when((in.name == "brainstorming") AND (in.properties.viewName ==
	// "clustering") AND (in.eventType ==
	// "BsServerNewIdeaCategory")).setVar(CATEGORY_COUNT++)

	private static final String inEventJson = "{\"name\":\"brainstorming\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"viewName\":\"clustering\",\"eventType\":\"BsServerNewIdeaCategory\"},\"customVars\":{}}";

	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();

		// expected
		Event expectedEvent = new Event();

		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.putAll(givenInstanceVars);
		expectedInstanceVars.put("CATEGORY_COUNT", 1);

		// test a: incr var not set before
		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);

		// test b: incr var set before
		InstanceVars givenInstanceVars1 = new InstanceVars();
		givenInstanceVars1.putAll(givenInstanceVars);
		givenInstanceVars1.put("CATEGORY_COUNT", 3);

		InstanceVars expectedInstanceVars1 = new InstanceVars();
		expectedInstanceVars1.putAll(expectedInstanceVars);
		expectedInstanceVars1.put("CATEGORY_COUNT", 4);
		addTestData(givenEvent, givenInstanceVars1, expectedEvent,
				expectedInstanceVars1);
	}

	@Override
	protected Rule getRule() {
		return new BsServerNewIdeaCategoryVarRule();
	}
}
