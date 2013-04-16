package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsServerRemoveIdeaFromCategoryVarRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"brainstorming\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"viewName\":\"clustering\",\"eventType\":\"BsServerAddIdeaToCategory\",\"CategoryId\":\"null\"},\"customVars\":{}}";

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
		expectedInstanceVars.put("TEMP_CATEGORIZED_IDEA_COUNT", 0);
		expectedInstanceVars.put("CATEGORIZED_IDEA_COUNT", 0);
		

		// test a: decr vars not set before
		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);

		// test b: incr var set before
		InstanceVars givenInstanceVars1 = new InstanceVars();
		givenInstanceVars1.put("TEMP_CATEGORIZED_IDEA_COUNT", 2);
		givenInstanceVars1.put("CATEGORIZED_IDEA_COUNT", 2);
		
		InstanceVars expectedInstanceVars1 = new InstanceVars();
		expectedInstanceVars1.put("TEMP_CATEGORIZED_IDEA_COUNT", 1);
		expectedInstanceVars1.put("CATEGORIZED_IDEA_COUNT", 1);
		addTestData(givenEvent, givenInstanceVars1, expectedEvent,
				expectedInstanceVars1);
	}

	@Override
	protected Rule getRule() {
		return new BsServerRemoveIdeaFromCategoryVarRule();
	}
}
