package de.fernuni.pi3.interactionmanager.rules;

import org.junit.Assert;
import org.junit.Test;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

/**
 * Contains tests for type {@link AbstractRule}.
 */
public class BasicRuleTest {
	
	@Test(expected = RequiredVarException.class)
	public void testGetRequiredVarOfWrongType() throws Exception {
		Rule testRule = getTestRule();
		InstanceVars var = new InstanceVars();
		var.put("testVar", "UnexpectedValueType");
		testRule.processEvent(new Event(), new Event(), var);
	}


	@Test(expected = RequiredVarException.class)
	public void testGetRequiredVarUnset() throws Exception {
		Rule testRule = getTestRule();
		InstanceVars var = new InstanceVars();
		testRule.processEvent(new Event(), new Event(), var);
	}

	@Test
	public void testGetRequiredVarRegular() throws Exception {
		Rule testRule = getTestRule();
		InstanceVars var = new InstanceVars();
		var.put("testVar", 3);
		Event outEvent = new Event();
		testRule.processEvent(new Event(), outEvent, var);
		Assert.assertEquals("Condition check passed without error",outEvent.getProperty("TEST_FEEDBACK"));
	}
	
	
	private AbstractRule getTestRule() {
		return new AbstractRule() {

			@Override
			public String getAppType() {
				return null;
			}

			@Override
			public int getIndex() {
				return 0;
			}

			@Override
			protected boolean ruleCondition(Event in, Event out,
					InstanceVars var) throws RequiredVarException {
				return (getRequiredVar(var, "testVar", Integer.class) > 2);
			}

			@Override
			protected void ruleBody(Event in, Event out, InstanceVars var) {
				out.setProperty("TEST_FEEDBACK", "Condition check passed without error");	
			}
			
		};
	}
	
	
	
	
}
