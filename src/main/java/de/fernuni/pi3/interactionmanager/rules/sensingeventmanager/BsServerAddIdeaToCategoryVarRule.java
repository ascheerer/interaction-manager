package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsServerAddIdeaToCategoryVarRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 25;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("brainstorming")
				&& "clustering".equals(in.getProperty("viewName")) && "BsServerAddIdeaToCategory"
					.equals(in.getProperty("eventType")) && "null".equals(in.getProperty("oldCategoryId")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		incrVar(var, "TEMP_CATEGORIZED_IDEA_COUNT");
		incrVar(var, "CATEGORIZED_IDEA_COUNT");
	}
	
}
