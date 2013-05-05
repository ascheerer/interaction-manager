package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsServerMoveIdeaToOtherCategoryVarRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 25;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("BsServerAddIdeaToCategory") && in.getCustomVar("appName").equals("brainstorming") && "clustering".equals(var.get("BRAINSTORMING_STEP")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		incrVar(var, "TEMP_CATEGORIZED_IDEA_COUNT");
	}
	
}
