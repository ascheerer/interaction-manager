package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsPriorizationResultVoteRule extends
		AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 7;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("participant")
				&& "userJoin".equals(in.getCustomVar("senderTime")) && "Planning"
					.equals(var.get("MEETING_TYPE")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("feedback");
		out.setProperty("eventId", 22);
		out.setProperty("type", "vote");
		out.setProperty("headline", "Sind Sie mit dem Ergebnis einverstanden?");
		out.setProperty("participantId", in.getProperty("participantId"));
	}

}