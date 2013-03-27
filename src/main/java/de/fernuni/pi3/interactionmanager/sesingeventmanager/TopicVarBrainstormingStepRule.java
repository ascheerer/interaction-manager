package de.fernuni.pi3.interactionmanager.sesingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class TopicVarBrainstormingStepRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 2;
	}

	@Override
	public void processEvent(Event in, Event out, InstanceVars var) {
		if (in.getName().equals("topic") && in.getProperty("topicApplication").equals("10")) {
			var.put("BRAINSTORMING_STEP", "ideation");
		}
	}

}
