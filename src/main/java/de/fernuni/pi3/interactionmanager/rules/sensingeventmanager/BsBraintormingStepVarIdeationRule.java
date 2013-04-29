package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsBraintormingStepVarIdeationRule extends AbstractSensingEventManagerRule {

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
		var.put("TEMP_IDEA_COUNT", 0);
		var.put("INITAL_RUN", true);
	}

}
