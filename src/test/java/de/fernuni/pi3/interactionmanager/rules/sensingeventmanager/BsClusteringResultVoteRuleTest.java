package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsClusteringResultVoteRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"brainstorming\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"eventType\":\"BsSwitchToNextIdeationView\",\"viewName\":\"clustering-result\"},\"customVars\":{}}";

	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();

		// expected
		Event expectedEvent = new Event();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("feedback");
		expectedEvent.setProperty("eventId", 3);
		expectedEvent.setProperty("headline", "Sind Sie mit dem Ergebnis einverstanden?");
		expectedEvent.setProperty("type", "Vote");
		
		InstanceVars expectedInstanceVars = new InstanceVars();

		// test a: incr vars not set before
		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);

	}

	@Override
	protected Rule getRule() {
		return new BsClusteringResultVoteRule();
	}
}
