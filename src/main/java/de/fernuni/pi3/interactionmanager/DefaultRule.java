package de.fernuni.pi3.interactionmanager;

import org.springframework.stereotype.Service;

@Service
class DefaultRule implements Rule {

	@Override
	public String getAppType() {
		return RuleService.DEFAULT_APPTYPE;
	}

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	public void processEvent(Event in, Event out, InstanceVars var) {
		
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
