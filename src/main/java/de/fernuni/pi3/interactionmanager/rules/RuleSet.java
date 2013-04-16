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
				String errorMsg = "Could not apply rule " + rule + " for event " + incomingEvent + ": " + e.getMessage();
				
				if (e instanceof RequiredVarException) {
					// no stacktrace required
					logger.error(errorMsg);
				} else {
					// unexpected error
					logger.error(errorMsg, e);
				}
				
				
				// TODO: replace hard coded error event for SensingEventEngine by a configurable one per appType
				Event errorEvent = new Event("recommendation");
				errorEvent.setAppType(incomingEvent.getAppType());
				errorEvent.setAppInstanceId(incomingEvent.getAppInstanceId());
				errorEvent.setProperty("eventId", 1);
				errorEvent.setProperty("headline", "Fehler beim Anwenden der Regel " + rule + " auf das Event " + incomingEvent);
				errorEvent.setProperty("text", "InteractionManager Fehlermeldung: " + errorMsg); 
				
				outgoingEvents.add(errorEvent);
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
	
	@Override
	public String toString() {
		String str = "RuleSet(";
		for (Rule rule : rules) {
			if (rule.getIndex() !=0) {
				str += "; ";
			}
			
			str += rule.getIndex() + "=" + rule.getClass().getName();
		}
		return str + ")";
	}
}
