package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class QuestionFeedbackRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 7;
	}


	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("topic") && "Planning".equals(var.get("MEETING_TYPE")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("feedback");
		out.setProperty("eventId", 21);
		out.setProperty("reActivation", true);
		out.setProperty("type", "question");
		out.setProperty("headline", "Möchten Sie eine Frage stellen?");
	}
	
}
