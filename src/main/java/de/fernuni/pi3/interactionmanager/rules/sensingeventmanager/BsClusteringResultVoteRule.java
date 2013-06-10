package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsClusteringResultVoteRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 31;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("brainstorming")
				&& "BsSwitchToNextIdeationView".equals(in
						.getProperty("eventType")) && "clustering-result"
					.equals(in.getProperty("viewName")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("feedback");
		out.setProperty("eventId", 3);
		out.setProperty("headline", "Sind Sie mit dem Ergebnis einverstanden?");
		out.setProperty("type", "Vote");
	}

}
