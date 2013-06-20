package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsBrainstormingStepVarRuleTest extends AbstractRuleTest {
	
	private static final String inEventJsonLastStep = "	{\"id\":\"\",\"name\":\"BsSwitchToNextIdeationView\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"516ed7a04958426303000001\",\"properties\":{\"previousFlowStepName\":\"ideation\",\"currentFlowStepName\":\"myTestBsStep\",\"nextFlowStepName\":\"priorizedIdeasList\",\"isNewProcesFlowStepTheLastStep\":true},\"customVars\":{\"appName\":\"brainstorming\"}}"; 
	private static final String inEventJsonNotLastStep = "	{\"id\":\"\",\"name\":\"BsSwitchToNextIdeationView\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"516ed7a04958426303000001\",\"properties\":{\"previousFlowStepName\":\"ideation\",\"currentFlowStepName\":\"myTestBsStep\",\"nextFlowStepName\":\"priorizedIdeasList\",\"isNewProcesFlowStepTheLastStep\":false},\"customVars\":{\"appName\":\"brainstorming\"}}"; 
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJsonNotLastStep);
		InstanceVars givenInstanceVars = new InstanceVars();
		
		// expected 
		Event expectedEvent = new Event();
		
		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.put("BRAINSTORMING_STEP", "myTestBsStep");
		expectedInstanceVars.put("INITAL_RUN", true);
		
		addTestData(givenEvent, givenInstanceVars, expectedEvent, expectedInstanceVars);
		
		Event givenLastStepEvent = Event.fromJson(inEventJsonLastStep);
		addTestData(givenLastStepEvent, new InstanceVars(), new Event(), new InstanceVars());
		
	}

	@Override
	protected Rule getRule() {
		return new BsBrainstormingStepVarRule();
	}
}
