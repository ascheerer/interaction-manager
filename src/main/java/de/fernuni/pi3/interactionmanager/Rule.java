package de.fernuni.pi3.interactionmanager;

import org.springframework.stereotype.Service;

@Service
public interface Rule {
	public String getAppType();
	public int getIndex();
	void processEvent(Event in, Event out, InstanceVars var);
}

