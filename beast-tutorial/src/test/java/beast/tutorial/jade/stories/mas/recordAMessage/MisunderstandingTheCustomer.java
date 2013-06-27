package beast.tutorial.jade.stories.mas.recordAMessage;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import jade.lang.acl.ACLMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import beast.tutorial.model.Call;
import beast.tutorial.model.CallQueue;
import beast.tutorial.model.Customer;
import es.upm.dit.gsi.beast.mock.MockManager;
import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;
import es.upm.dit.gsi.beast.mock.common.Definitions;
import es.upm.dit.gsi.beast.mock.common.MockConfiguration;
import es.upm.dit.gsi.beast.platform.jade.JadeConnector;
import es.upm.dit.gsi.beast.story.BeastTestCase;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language. In the GIVEN part it launchs the platform In the WHEN part it
 * configures the state of its agents. In the THEN part it checks the correct
 * behaviour. The main purpose of it consists of knowing agents'
 * state/properties without changing its code.
 * 
 * 
 * This "AgentStory" is described as follows: Story: Record a message As a
 * RecorderAgent, I want to record incoming calls, So that I can pass the
 * message to a ReporterAgent.
 * 
 * This specific scenario is described as follows: Scenario:
 * MisunderstandingTheCustomer Given a RecordAgent and a HelpDeskAgent can
 * communicate between them, When a phone call is received and the RecorderAgent
 * does not understand the customer, Then the RecorderAgent sends a FIPA-REQUEST
 * message to a HelpDeskAgent and the RecorderAgent pass the incoming call to a
 * HelpDeskAgent.
 * 
 * @author es.upm.dit.gsi.beast
 */
public class MisunderstandingTheCustomer extends BeastTestCase {

	public Logger logger = Logger.getLogger(MisunderstandingTheCustomer.class
			.getName());

	/**
	 * Constructor to configure logging
	 */
	public MisunderstandingTheCustomer() {
		Properties preferences = new Properties();
		try {
			FileInputStream configFile = new FileInputStream(
					"src/test/resources/beast-conf/logger.properties");
			preferences.load(configFile);
			LogManager.getLogManager().readConfiguration(configFile);
			LogActivator.logToFile(logger,
					MisunderstandingTheCustomer.class.getName(), Level.ALL);
		} catch (IOException ex) {
			logger.severe("WARNING: Could not open configuration file");
		}
	}

	/**
	 * This is the method that must create the Scenario. It is related with the
	 * GIVEN part.
	 * "GIVEN a RecordAgent and a HelpDeskAgent can communicate between them,".
	 * 
	 * In setup method the following method must be used
	 * startAgent(agent_name,agent_path)
	 */
	public void setup() {

		// HelpDeskMockAgent configuration

		MockConfiguration repositoryMockConfiguration = new MockConfiguration();
		repositoryMockConfiguration.setDFServiceName("helpdesk-service");

		// Add a mocked AgentBehaviour.
		AgentBehaviour repositoryMockBehaviour = mock(AgentBehaviour.class);
		when(
				repositoryMockBehaviour.processMessage(
						eq(ACLMessage.getPerformative(ACLMessage.REQUEST)),
						eq("RecorderAgentUnderTesting"),
						eq("UnknownLanguageCall"))).thenReturn("Agree");
		repositoryMockConfiguration.setBehaviour(repositoryMockBehaviour);
		// Start the agent.
		MockManager.startMockJadeAgent("HelpDeskMockAgent",
				Definitions.JADE_REPOSITORY_MOCK_PATH,
				repositoryMockConfiguration, this);

		// Launch RecordAgent
		startAgent("RecorderAgentUnderTesting",
				"beast.tutorial.jade.agent.RecorderAgent", "MyContainer", null);
	}

	/**
	 * This is the method that must create the Setup. It is related with the
	 * WHEN part.
	 * "WHEN a phone call is received and the RecorderAgent does not understand the customer,"
	 * 
	 * In launch method the following methods must be used setBeliefValue
	 * (agent_name, belief_name, new_value ) sendMessageToAgent(agent_name,
	 * msgtype, message_content) getAgentPlans(agent_name)
	 * getAgentGoals(agent_name )
	 */
	public void launch() {

		// Creating mocks objects
		CallQueue queue = mock(CallQueue.class);
		Call call = mock(Call.class);
		Customer customer = mock(Customer.class);

		// Defining mock behaviours
		when(customer.getLanguage()).thenReturn("Spanish");
		when(call.getCustormer()).thenReturn(customer);
		when(queue.getPendingCall()).thenReturn(call).thenReturn(null);

		setBeliefValue("RecorderAgentUnderTesting", "queue", queue);

	}

	/**
	 * This is the method that must create the Evaluation. It is related with
	 * the THEN part.
	 * "THEN the RecorderAgent sends a FIPA-REQUEST message to a HelpDeskAgent and the RecorderAgent pass the incoming call to a HelpDeskAgent."
	 * 
	 * In verify method the following method must be used
	 * checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
	 */
	public void verify() {

		setExecutionTime(2000);

		checkAgentsBeliefEquealsTo("RecorderAgentUnderTesting", "passedCall", true);
		checkAgentsBeliefEquealsTo("HelpDeskMockAgent", Definitions.RECEIVED_MESSAGE_COUNT, 1);

	}

	/**
	 * The GIVEN part
	 */
	@Given("$scenarioName")
	public void createScenario(String scenarioName) {

		if (scenarioName
				.equals("a RecordAgent and a HelpDeskAgent can communicate between them,")) {
			startPlatform("jade", logger);
		} else {
			logger.severe("WARNING: "
					+ scenarioName
					+ " does not coincide with a RecordAgent and a HelpDeskAgent can communicate between them,");
		}
	}

	/**
	 * The WHEN part
	 */
	@When("$setupName")
	public void configureScenario(String setupName) {

		if (setupName
				.equals("a phone call is received and the RecorderAgent does not understand the customer,")) {
			setScenario();
		} else {
			logger.severe("WARNING: "
					+ setupName
					+ " does not coincide with a phone call is received and the RecorderAgent does not understand the customer,");
		}
	}

	/**
	 * The THEN part, where the correct behaviour must be asserted
	 */
	@Then("$evaluationName")
	public void checkScenario(String evaluationName) {

		if (evaluationName
				.equals("the RecorderAgent sends a FIPA-REQUEST message to a HelpDeskAgent and the RecorderAgent pass the incoming call to a HelpDeskAgent.")) {
			this.setExecutionTime(BeastTestCase.SLEEP_TIME);
			verify();
		} else {
			logger.severe("WARNING: "
					+ evaluationName
					+ " does not coincide with the RecorderAgent sends a FIPA-REQUEST message to a HelpDeskAgent and the RecorderAgent pass the incoming call to a HelpDeskAgent.");
		}
	}
    
    /**
     * Stop the agent platform.
     */
    @AfterScenario
    public void cleanUp() {
    	super.getConnector().stopPlatform();
    }

}
