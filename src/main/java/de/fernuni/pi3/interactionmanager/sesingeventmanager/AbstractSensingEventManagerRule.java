package de.fernuni.pi3.interactionmanager.sesingeventmanager;

import de.fernuni.pi3.interactionmanager.Rule;

public abstract class AbstractSensingEventManagerRule implements Rule {

	@Override
	public String getAppType() {
		return "SensingEventManager";
	}
}
