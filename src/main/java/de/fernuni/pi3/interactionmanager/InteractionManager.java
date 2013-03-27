package de.fernuni.pi3.interactionmanager;

import java.util.List;

import org.apache.camel.Consume;
import org.apache.camel.InOnly;
import org.apache.camel.Produce;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.rules.RuleService;

@Service
public class InteractionManager {

	private static final String WEBSOCKET_URI = "websocket://interaction-manager?sendToAll=true";

	@Autowired
	private RuleService ruleService;

	interface MessageSender {
		@InOnly
		String sendMessage(String msg);
	}

	@Produce(uri = WEBSOCKET_URI)
	MessageSender sender;

	private static Logger logger = Logger.getLogger(InteractionManager.class);

	@Consume(uri = WEBSOCKET_URI)
	public void receiveMessage(String message) {
		Event originalEvent;
		try {
			originalEvent = Event.fromJson(message);
		} catch (Exception e) {
			logger.warn("Could not parse incoming event: " + e.getMessage()
					+ ". Inconming string message: " + message);
			return;
		}

		logger.info("Received event: " + originalEvent);

		List<Event> outgoingEvents = ruleService.getRuleSet(
				originalEvent.getAppType()).processEvent(originalEvent);

		for (Event event : outgoingEvents) {
			logger.info("Sending event: " + event);
			sender.sendMessage(event.toJson());
		}

	}
}
