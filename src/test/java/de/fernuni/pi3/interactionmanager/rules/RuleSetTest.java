package de.fernuni.pi3.interactionmanager.rules;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

public class RuleSetTest {

	
	@Test
	public void testErrorEvent() {
		
		
		Rule testRule = getFailingRule();
		
		ArrayList<Rule> testRules = new ArrayList<Rule>();
		testRules.add(testRule);
		
		Event testEvent = new Event("testEvent");
		testEvent.setAppType("Test");
		testEvent.setAppInstanceId("RuleSetTest");
		
		RuleSet ruleSet = new RuleSet(testRules);
		List<Event> outgoingEvents = ruleSet.processEvent(testEvent);
		
		Assert.assertEquals("Expected exactly one outgoing event.", 1, outgoingEvents.size());
		Event errorEvent = outgoingEvents.get(0);
		Assert.assertEquals("recommendation", errorEvent.getName());
		Assert.assertEquals(1, errorEvent.getProperty("eventId"));
		Assert.assertEquals(testEvent.getAppType(), errorEvent.getAppType());
		Assert.assertEquals(testEvent.getAppInstanceId(), errorEvent.getAppInstanceId());
		Assert.assertEquals("Fehler beim Anwenden der Regel " + testRule + " auf das Event " + testEvent, errorEvent.getProperty("headline"));
		Assert.assertTrue(((String)errorEvent.getProperty("text")).startsWith("InteractionManager Fehlermeldung: Could not apply rule " + testRule));
		Assert.assertTrue(((String)errorEvent.getProperty("text")).contains("TestFallbackErrorEventMsg"));
	}

	private Rule getFailingRule() {
		return new Rule() {
			
			@Override
			public void processEvent(Event in, Event out, InstanceVars var) {
				throw new RuntimeException("TestFallbackErrorEventMsg");
			}
			
			@Override
			public int getIndex() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getAppType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
