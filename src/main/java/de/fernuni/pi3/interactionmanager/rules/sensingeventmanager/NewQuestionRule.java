package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class NewQuestionRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 4;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("feedbackAssistant")
				&& "vote".equals(in.getProperty("feedbackType")) && ((Double) in
					.getProperty("feedbackAnswerRating")) == 1.0);
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) throws RequiredVarException {
		// increase var QUESTION_COUNT
		incrVar(var, "QUESTION_COUNT");

		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 1);
		out.setProperty(
				"headline",
				"Der Teilnehmer "
						+ in.getProperty("participantName") + " ist einverstanden");
		out.setProperty(
				"text",
				"Das Ergebnis der Priorisierung wurde von dem Teilnehmer positiv abgestimmt.");
		HashMap<String, String> options = new HashMap<String,String>();
		options.put("Fortfahren", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.continue");
		out.setProperty("options", options);
	}
}
