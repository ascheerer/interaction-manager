package de.fernuni.pi3.interactionmanager.rules;

import org.apache.log4j.Logger;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

abstract public class AbstractRule implements Rule {

	private static Logger logger = Logger.getLogger(AbstractRule.class);

	@Override
	public void processEvent(Event in, Event out, InstanceVars var) throws RequiredVarException{
		if (!ruleCondition(in, out, var)) {
			logger.debug("Rule condition is false -> skipping body of rule " + this);
			return;
		}

		logger.info("Rule condition is true -> executing body of rule " + this);
		ruleBody(in, out, var);
	}

	abstract protected boolean ruleCondition(Event in, Event out,
			InstanceVars var) throws RequiredVarException;

	abstract protected void ruleBody(Event in, Event out, InstanceVars var) throws RequiredVarException;

	@Override
	public String toString() {
		return "Rule(index=" + getIndex() + "; appType=" + getAppType()
				+ "; class=" + this.getClass().getName() + ")";
	}
	
	protected final void incrVar(InstanceVars var, String varName) {
		if (var.get(varName) == null) {
			var.put(varName, 1);
			return;
		}
		
		var.put(varName, (Integer) var.get(varName) + 1);
	}
	
	protected final void decrVar(InstanceVars var, String varName) {
		if (var.get(varName) == null || (Integer) var.get(varName) < 1) {
			var.put(varName, 0);
			return;
		}
		
		var.put(varName, (Integer) var.get(varName) - 1);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getRequiredVar(InstanceVars var, String varName, Class<T> clazz) throws RequiredVarException {
		if (! var.containsKey(varName)) {
			throw new RequiredVarException("Required instance var '" + varName + "' not set in rule " + this);
		}
		
		if (! var.get(varName).getClass().equals(clazz)) {
			throw new RequiredVarException("Instance var '" + varName + "' is not of expected type " + clazz.getName() + " but of type " + var.get(varName).getClass().getName());
		}
		return (T) var.get(varName);
	}
}
