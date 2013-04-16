package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class NewQuestionRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 4;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("feedbackAssistant")
				&& "question".equals(in.getProperty("feedbackType")) && ((Double) in
					.getProperty("feedbackAnswerRating")) == 1.0);
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		// increase var QUESTION_COUNT
		if (var.get("QUESTION_COUNT") != null) {
			var.put("QUESTION_COUNT",
					getRequiredVar(var,"QUESTION_COUNT",Integer.class) + 1);
		} else {
			var.put("QUESTION_COUNT", 1);
		}

		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 1);
		out.setProperty(
				"headline",
				"Neue eingehende Frage von "
						+ in.getProperty("participantName"));
		out.setProperty(
				"text",
				"Bitte rufen Sie "
						+ in.getProperty("participantName")
						+ " auf und bitten Sie den Teilnehmer, die Frage zu stellen.");
		HashMap<String, String> options = new HashMap<String,String>();
		options.put("Fortfahren", "continue");
		out.setProperty("options", options);
	}
}
