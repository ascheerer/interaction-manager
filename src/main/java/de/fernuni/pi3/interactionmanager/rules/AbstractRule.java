package de.fernuni.pi3.interactionmanager.rules;

import org.apache.log4j.Logger;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

abstract public class AbstractRule implements Rule {

	private static Logger logger = Logger.getLogger(AbstractRule.class);

	@Override
	public void processEvent(Event in, Event out, InstanceVars var) {
		if (!ruleCondition(in, out, var)) {
			logger.debug("Rule condition is false -> skipping body of rule " + this);
			return;
		}

		logger.info("Rule condition is true -> executing body of rule " + this);
		ruleBody(in, out, var);
	}

	abstract protected boolean ruleCondition(Event in, Event out,
			InstanceVars var);

	abstract protected void ruleBody(Event in, Event out, InstanceVars var);

	@Override
	public String toString() {
		return "Rule(index=" + getIndex() + "; appType=" + getAppType()
				+ "; class=" + this.getClass().getName() + ")";
	}
}
