package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsNotEnoughIdeasRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"duration\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{},\"customVars\":{}}";
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("TEMP_IDEA_COUNT", SensingEventManagerConsts.MIN_IDEA_COUNT);
		givenInstanceVars.put("TOPIC_APPLICATION", "10");
		givenInstanceVars.put("BRAINSTORMING_STEP", "ideation");
		givenInstanceVars.put("INITAL_RUN", false);

		// expected
		Event expectedEvent = createTestEvent();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("recommendation");
		expectedEvent.setProperty("eventId", 43);
		expectedEvent
				.setProperty("headline", "Die Meeting-Teilnehmer generieren keine neuen Ideen.");
		expectedEvent
				.setProperty(
						"text",
						"Was wollen Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Fortfahren",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.continue");
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
		expectedInstanceVars.put("INITAL_RUN", true);
		
		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new BsNotEnoughIdeasRule();
	}
}
