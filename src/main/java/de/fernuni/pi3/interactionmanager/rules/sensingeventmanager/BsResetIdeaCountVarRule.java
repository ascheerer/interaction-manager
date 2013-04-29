package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class BsResetIdeaCountVarRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 22;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var)
			throws RequiredVarException {
		return (in.getName().equals("duration")
				&& "10".equals(var.get("TOPIC_APPLICATION"))
				&& "ideation".equals(var.get("BRAINSTORMING_STEP")) && (getRequiredVar(
				var, "TEMP_IDEA_COUNT", Integer.class) > SensingEventManagerConsts.MIN_IDEA_COUNT));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		var.put("TEMP_IDEA_COUNT", 0);
		var.put("INITAL_RUN", false);
	}

}
