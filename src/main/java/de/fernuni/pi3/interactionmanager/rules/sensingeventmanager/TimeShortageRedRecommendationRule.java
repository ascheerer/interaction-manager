package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class TimeShortageRedRecommendationRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 6;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) throws RequiredVarException {
		return (in.getName().equals("duration")
				&& (var.get("TOPIC_APPLICATION") != null) && ((Double) in
				.getProperty("timePast")
				- getRequiredVar(var, "TOPIC_START", Double.class) >= 0.9 * getRequiredVar(
				var, "TOPIC_DURATION", Double.class)  && (getRequiredVar(
					var, "SHORTAGE_MESSAGE", Boolean.class) == false)));
	}
	
	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
			var.put("SHORTAGE_MESSAGE", true);
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 47);
		out.setProperty("headline",
				"Die Zeit für den aktuellen Agendapunkt ist abgelaufen.");
		out.setProperty("text",
				"Wollen Sie mit dem aktuellen Agendapunkt fortfahren oder zum nächsten Punkt übergehen?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Fortfahren", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.continue");
				options.put("Zur Agendaübersicht","Meetingstar.util.global.sensingEngine.MagicButtonFunctions.backToAgenda");
				
		out.setProperty("options", options);
	}
}