package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class BsResetCategorizedIdeaCountVarRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 29;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) throws RequiredVarException {
		return (in.getName().equals("duration")
				&& (getRequiredVar(var, "TEMP_CATEGORIZED_IDEA_COUNT", Integer.class) > SensingEventManagerConsts.MIN_CATEGORIZED_IDEA_COUNT)
				&& "10".equals(var.get("TOPIC_APPLICATION"))
				&& "clustering".equals(var.get("BRAINSTORMING_STEP")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		var.put("TEMP_CATEGORIZED_IDEA_COUNT", 0);
	}

}
