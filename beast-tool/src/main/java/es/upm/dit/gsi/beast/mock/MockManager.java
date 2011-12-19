package es.upm.dit.gsi.beast.mock;

import jadex.base.fipa.SFipa;
import es.upm.dit.gsi.beast.mock.jadex.common.MockConfiguration;
import es.upm.dit.gsi.beast.story.testCase.Scenario;

public class MockManager {

	public static void startMockJadexAgent(String name, String type,
			MockConfiguration configuration, Scenario scenario) {
		scenario.startAgent(name, type);
		scenario.sendMessageToAgent(name, SFipa.INFORM, configuration);
	}

}