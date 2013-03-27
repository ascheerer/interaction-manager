package de.fernuni.pi3.interactionmanager.sesingeventmanager;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

public class TopicVarsAndResetFeedbackRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 1;
	}

	@Override
	public void processEvent(Event in, Event out, InstanceVars var) {
		if (in.getName().equals("topic")) {
			var.put("TOPIC_APPLICATION", in.getProperty("topicApplication"));
			var.put("TOPIC_DURATION", in.getProperty("topicDuration"));
			
			out.setAppType(in.getAppType());
			out.setAppInstanceId(in.getAppInstanceId());
			out.setName("feedback");
			out.setProperty("eventId","3");
			out.setProperty("type", "reset");
		}
	}

}
