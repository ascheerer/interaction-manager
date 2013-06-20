package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsUserPriorizedAllIdeasRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"BsUserPriorizedAllIdeas\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"viewName\":\"clustering\",\"eventType\":\"BsUserPriorizedAllIdeas\"},\"customVars\":{\"appName\":\"brainstorming\"}}";
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("BRAINSTORMING_STEP", "priorization");
		

		// expected
		Event expectedEvent = createTestEvent();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("recommendation");
		expectedEvent.setProperty("eventId", 44);
		expectedEvent.setProperty("headline",
				"Die Meeting-Teilnehmer haben alle Ideen priorisiert.");
		expectedEvent
				.setProperty(
						"text",
						"Was möchten Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Abbrechen",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.cancel");
		options.put("Meeting beenden",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.quit");
		options.put("Brainstorming beenden",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.backToAgenda");
		options.put("Eine Nachricht an alle Teilnehmer senden",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.sendMessage");
		options.put("Wechseln zur nächsten Phase",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.bsNextStep");
		expectedEvent.setProperty("options", options);

		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.putAll(givenInstanceVars);

		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new BsUserPriorizedAllIdeasRule();
	}
}
