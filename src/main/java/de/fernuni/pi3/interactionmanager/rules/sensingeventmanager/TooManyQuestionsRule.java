package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class TooManyQuestionsRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 12;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var)
			throws RequiredVarException {
		return (in.getName().equals("feedbackAssistant")
				&& "Planning".equals(var.get("MEETING_TYPE")) && (getRequiredVar(
				var, "QUESTION_COUNT", Integer.class) > SensingEventManagerConsts.MAX_QUESTIONS_COUNT));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 42);
		out.setProperty("headline",
				"Die maximale Anzahl der gestellten Fragen wurde überschritten.");
		out.setProperty("text",
				"Wollen Sie den Wert zurücksetzen und fortfahren?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Zählung zurücksetzen und fortfahren", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.resetCount");
		options.put("Meeting beenden", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.quit");
		options.put("Abbrechen", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.cancel");
		out.setProperty("options", options);
	}

}
