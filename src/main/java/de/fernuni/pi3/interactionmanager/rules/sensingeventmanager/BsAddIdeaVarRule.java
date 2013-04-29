package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsAddIdeaVarRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 20;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("BsServerNewIdea")
				&& "brainstorming".equals(in.getCustomVar("appName")) && "ideation"
					.equals(var.get("BRAINSTORMING_STEP")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		incrVar(var, "IDEA_COUNT");
		incrVar(var, "TEMP_IDEA_COUNT");
	}
	
}
