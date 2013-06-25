package es.upm.dit.gsi.beast.platform.jadex;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Main class to launch all tests in a single run
 *
 * @author es.upm.dit.gsi.beast
 */
public class AgentStoriesManager {

  /**
   * This is the story: jadex platform test,
   * requested by: developer
   * providing the feature: generate tests for the agent
   * so the user gets the benefit: i save effort
   */
  @Test
  public void jadexPlatformTest() {
      Result result = JUnitCore.runClasses(es.upm.dit.gsi.beast.platform.jadex.JadexPlatformTest.class);
      Assert.assertTrue(result.wasSuccessful());
  }

}
