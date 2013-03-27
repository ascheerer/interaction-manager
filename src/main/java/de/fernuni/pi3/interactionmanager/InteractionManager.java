package de.fernuni.pi3.interactionmanager;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Consume;
import org.apache.camel.InOnly;
import org.apache.camel.Produce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	private static Logger logger = Logger.getLogger(Event.class.getName());

	@Consume(uri = WEBSOCKET_URI)
	public void receiveMessage(String message) {
		Event originalEvent;
		try {
			originalEvent = Event.fromJson(message);
		} catch (Exception e) {
			logger.log(Level.WARNING,
					"Could not parse incoming event: " + e.getMessage()
							+ ". Inconming string message: " + message);
			return;
		}

		logger.log(Level.INFO, "Received event: " + originalEvent.toJson());

		List<Event> outgoingEvents = ruleService.getRuleSet(
				originalEvent.getAppType()).processEvent(originalEvent);

		for (Event event : outgoingEvents) {
			logger.log(Level.INFO, "Sending event: " + event.toJson());
			sender.sendMessage(event.toJson());
		}

	}
}
