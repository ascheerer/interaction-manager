package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class ResetInstanceStateRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("resetInstanceState"));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		var.clear();
		var.put("QUESTION_COUNT", 0);
	}
}
