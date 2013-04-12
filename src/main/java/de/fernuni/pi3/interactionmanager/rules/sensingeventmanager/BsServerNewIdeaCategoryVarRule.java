package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsServerNewIdeaCategoryVarRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 24;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("brainstorming")
				&& "clustering".equals(in.getProperty("viewName")) && "BsServerNewIdeaCategory"
					.equals(in.getProperty("eventType")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		incrVar(var, "CATEGORY_COUNT");
	}
	
}
