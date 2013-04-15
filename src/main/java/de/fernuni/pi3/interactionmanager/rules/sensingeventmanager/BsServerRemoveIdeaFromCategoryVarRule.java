package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsServerRemoveIdeaFromCategoryVarRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 26;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("brainstorming")
				&& "clustering".equals(in.getProperty("viewName")) && "BsServerAddIdeaToCategory"
					.equals(in.getProperty("eventType")) && "null".equals(in.getProperty("CategoryId")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		decrVar(var, "TEMP_CATEGORIZED_IDEA_COUNT");
		decrVar(var, "CATEGORIZED_IDEA_COUNT");
	}
	
}
