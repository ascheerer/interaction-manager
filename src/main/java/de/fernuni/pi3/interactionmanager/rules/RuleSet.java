package de.fernuni.pi3.interactionmanager.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

public class RuleSet {

	private static Logger logger = Logger.getLogger(RuleSet.class);

	private Map<String, InstanceVars> instanceVars = new HashMap<String, InstanceVars>();

	private List<Rule> rules;

	RuleSet(List<Rule> rules) {
		this.rules = rules;

		Collections.sort(this.rules, new Comparator<Rule>() {

			@Override
			public int compare(Rule r1, Rule r2) {
				if (r1.getIndex() < r2.getIndex()) {
					return -1;
				} else if (r1.getIndex() > r2.getIndex()) {
					return 1;
				} else {
					return 0;
				}

			}
		});

	}

	public List<Event> processEvent(Event incomingEvent) {

		initInstanceVars(incomingEvent);
		ArrayList<Event> outgoingEvents = new ArrayList<Event>();

		for (Rule rule : rules) {

			logger.debug("Applying rule " + rule + " for event " + incomingEvent);

			Event outgoingEvent = new Event();

			try {
				rule.processEvent(incomingEvent, outgoingEvent,
						instanceVars.get(incomingEvent.getAppInstanceId()));

			} catch (Exception e) {
				logger.error("Could not apply rule " + rule + " for event " + incomingEvent + ": " + e.getMessage(), e);
			}

			if (!outgoingEvent.isEmpty()) {
				outgoingEvents.add(outgoingEvent);
			}

		}

		logger.info("Instance state after rule processing: InstanceVars(appInstanceId="
				+ incomingEvent.getAppInstanceId() + "; vars="
				+ instanceVars.get(incomingEvent.getAppInstanceId()) + ")");

		return outgoingEvents;
	}

	private void initInstanceVars(Event event) {
		if (!instanceVars.containsKey(event.getAppInstanceId())) {
			instanceVars.put(event.getAppInstanceId(), new InstanceVars());
		}
	}

}
