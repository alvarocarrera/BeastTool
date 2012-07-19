package es.upm.dit.gsi.beast.platform.jadex;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import es.upm.dit.gsi.beast.story.BeastTestCaseRunner;

/**
 * Main class to launch all tests in a single run
 *
 * @author es.upm.dit.gsi.beast
 */
public class CaseManager {

  /**
   * This is the story: jadex platform test,
   * requested by: developer,
   * providing the feature: generate tests for the agent
   * so the user gets the benefit: null
   */
  @Test
  public void jadexPlatformTest() {
      JUnitCore.main("es.upm.dit.gsi.beast.platform.jadex.JadexPlatformTest");
  }

}
