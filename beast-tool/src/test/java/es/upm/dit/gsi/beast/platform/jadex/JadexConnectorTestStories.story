Scenario: Set believes in agent
Given that one agent is started in Jadex Platform
When tester wants to set a belief inside a Jadex agent
Then the Jadex belief is set

Scenario: Get believes from agent
Given that one agent is started in Jadex Platform
When tester wants to get a belief from a Jadex agent
Then the Jadex belief is retrieved