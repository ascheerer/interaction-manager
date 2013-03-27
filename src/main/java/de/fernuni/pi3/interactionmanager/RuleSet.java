package de.fernuni.pi3.interactionmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class RuleSet {

	private static Logger logger = Logger.getLogger(RuleSet.class.getName());

	private Map<String, InstanceVars> instanceVars = new HashMap<String, InstanceVars>();

	private List<Rule> rules;

	RuleSet(List<Rule> rules) {
		this.rules = rules;
	}

	List<Event> processEvent(Event incomingEvent) {

		initInstanceVars(incomingEvent);
		ArrayList<Event> outgoingEvents = new ArrayList<Event>();

		for (Rule rule : rules) {

				Event outgoingEvent = new Event();
				rule.processEvent(incomingEvent, outgoingEvent,
						instanceVars.get(incomingEvent.getAppInstanceId()));
				
				if (!outgoingEvent.isEmpty()) {
					outgoingEvents.add(outgoingEvent);
				}
		}

		logger.info("Instance vars for instance id "
				+ incomingEvent.getAppInstanceId() + ": "
				+ instanceVars.get(incomingEvent.getAppInstanceId()));

		return outgoingEvents;
	}

	private void initInstanceVars(Event event) {
		if (!instanceVars.containsKey(event.getAppInstanceId())) {
			instanceVars.put(event.getAppInstanceId(),
					new InstanceVars());
		}
	}

}
