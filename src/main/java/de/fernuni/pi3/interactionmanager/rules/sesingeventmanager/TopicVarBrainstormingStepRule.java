package de.fernuni.pi3.interactionmanager.rules.sesingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class TopicVarBrainstormingStepRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 3;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("topic") && "10".equals(in.getProperty("topicApplication")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		var.put("BRAINSTORMING_STEP", "ideation");
	}

}
