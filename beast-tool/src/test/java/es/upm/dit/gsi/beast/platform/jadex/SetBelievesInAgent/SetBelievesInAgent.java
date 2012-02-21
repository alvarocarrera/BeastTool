package es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import es.upm.dit.gsi.beast.story.Story;
import es.upm.dit.gsi.beast.story.logging.LogActivator;

/**
 * Main class to translate plain text into code, following the Given-When-Then
 * language. In the GIVEN part it launchs the platform In the WHEN part it
 * configures the state of its agents. In the THEN part it checks the correct
 * behaviour. The main purpose of it consists of knowing agents' state/properties
 * without changing its code.
 * 
 * @author es.upm.dit.gsi.beast
 */
  public class SetBelievesInAgent extends Story {

     public Logger logger = Logger.getLogger(SetBelievesInAgent.class.getName());
  /**
   * Constructor to configure logging
   */
  public SetBelievesInAgent() {
     Properties preferences = new Properties();
     try {
         FileInputStream configFile = new FileInputStream("src/test/java/es/upm/dit/gsi/beast/platform/jadex/jadexBeastLog.properties");
         preferences.load(configFile);
         LogManager.getLogManager().readConfiguration(configFile);
         LogActivator.logToFile(logger, SetBelievesInAgent.class.getName(), Level.ALL);
     } catch (IOException ex) {
         logger.severe("WARNING: Could not open configuration file");
     }
  }

 	/**
	 * The GIVEN part
	 */
	@Given("$scenarioName")
	public void createScenario(String scenarioName) {

      if (scenarioName.equals("that one agent is started in Jadex Platform")){
		    super.createScenario("es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent.phases.Scenario", "jadex", logger);
      } else {
          logger.severe("WARNING: "+scenarioName+" does not coincide with that one agent is started in Jadex Platform" );
      }
	}

  /**
   * The WHEN part
   */
  @When("$setupName")
  public void configureScenario(String setupName) {

      if (setupName.equals("tester wants to set a belief inside a Jadex agent")){
          super.setup("es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent.phases.Setup");
      } else {
          logger.severe("WARNING: "+setupName+" does not coincide with tester wants to set a belief inside a Jadex agent");
      }
  }

  /**
   * The THEN part, where the correct behaviour must be asserted 
   */
  @Then("$evaluationName")
  public void checkScenario(String evaluationName) {

      if (evaluationName.equals("the Jadex belief is set")){
          super.executeEvaluation("es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent.phases.Evaluation");
      } else {
          logger.severe("WARNING: "+evaluationName+" does not coincide with the Jadex belief is set");
      }
  }

}

