package de.fernuni.pi3.interactionmanager.rules.sesingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class TopicVarsAndResetFeedbackRule extends
		AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 2;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("topic"));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		var.put("TOPIC_APPLICATION", in.getProperty("topicApplication"));
		var.put("TOPIC_DURATION", in.getProperty("topicDuration"));

		if (var.get("TIME_PAST") != null) {
			var.put("TOPIC_START", var.get("TIME_PAST"));
		} else {
			var.put("TOPIC_START", 0.0);
		}

		var.put("QUESTION_COUNT", 0);

		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("feedback");
		out.setProperty("eventId", 3);
		out.setProperty("type", "reset");
	}

}
