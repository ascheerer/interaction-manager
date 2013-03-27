package de.fernuni.pi3.interactionmanager.rules;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
class DefaultRule extends AbstractRule {

	@Override
	public String getAppType() {
		return RuleService.DEFAULT_APPTYPE;
	}

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		// since we want to echo on all incoming events, condition is true
		// in any case
		return true;
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		// copy original event
		out.setName(in.getName());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setAppType(in.getAppType());
		out.setCustomVars(in.getCustomVars());
		out.setProperties(in.getProperties());

		// increase countEvents
		if (var.containsKey("countEvents")) {
			int countEvents = (Integer) var.get("countEvents");
			var.put("countEvents", ++countEvents);
		} else {
			var.put("countEvents", 1);
		}
	}
}
