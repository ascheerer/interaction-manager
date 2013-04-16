package de.fernuni.pi3.interactionmanager.rules;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public interface Rule {
	public String getAppType();
	public int getIndex();
	public void processEvent(Event in, Event out, InstanceVars var) throws RequiredVarException;
}

