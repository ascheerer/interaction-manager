package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsResetCategorizedIdeaCountVarRuleTest extends AbstractRuleTest {
	
	private static final String inEventJson = "{\"name\":\"duration\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{},\"customVars\":{}}";
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("TEMP_CATEGORIZED_IDEA_COUNT", SensingEventManagerConsts.MIN_CATEGORIZED_IDEA_COUNT + 1);
		givenInstanceVars.put("TOPIC_APPLICATION", "10");
		givenInstanceVars.put("BRAINSTORMING_STEP", "clustering");

		
		// expected
		Event expectedEvent = new Event();

		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.putAll(givenInstanceVars);
		expectedInstanceVars.put("TEMP_CATEGORIZED_IDEA_COUNT", 0);
		expectedInstanceVars.put("INITAL_RUN", false);
		
		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new BsResetCategorizedIdeaCountVarRule();
	}
}
