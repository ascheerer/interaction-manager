package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsBrainstormingStepVarRule extends AbstractSensingEventManagerRule {
	
	@Override
	public int getIndex() {
		return 23;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return in.getName().equals("BsSwitchToNextIdeationView") && in.getCustomVar("appName").equals("brainstorming")
			&& !((Boolean) in.getProperty("isNewProcesFlowStepTheLastStep"));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		var.put("BRAINSTORMING_STEP", in.getProperty("currentFlowStepName"));
		var.put("INITAL_RUN", true);
	}
}