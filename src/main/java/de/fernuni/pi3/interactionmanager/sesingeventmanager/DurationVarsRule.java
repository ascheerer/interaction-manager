package de.fernuni.pi3.interactionmanager.sesingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class DurationVarsRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	public void processEvent(Event in, Event out, InstanceVars var) {
		if (in.getName().equals("duration")) {
			var.put("TIME_PAST", in.getProperty("timePast"));
			var.put("TIME_LEFT", in.getProperty("timeLeft"));
			var.put("PULSE_COUNT", in.getProperty("pulseCount"));
		}
	}
}
