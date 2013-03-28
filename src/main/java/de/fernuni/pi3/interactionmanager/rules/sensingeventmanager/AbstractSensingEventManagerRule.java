package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import de.fernuni.pi3.interactionmanager.rules.AbstractRule;


public abstract class AbstractSensingEventManagerRule extends AbstractRule {

	@Override
	public String getAppType() {
		return "SensingEventManager";
	}
	
}
