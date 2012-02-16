Scenario: Set believes in agent
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 4
When tester wants to set a belief inside a Jade agent
Then the Jade belief is set

Scenario: Get believes from agent
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 1
When tester wants to get a belief from a Jade agent
Then the Jade belief is retrieved

Scenario: Send a message
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 5
When tester wants to send a message with MessageService to TesterAgent
Then the message is received by TesterAgent

Scenario: Messenger Agent receive a response
Given that TesterAgent is started in Jade Platform in Main-Container with Configuration 6
When tester wants to send a message with MessageService to TesterAgent and to receive a response 
Then the message is received by the tester through MessengerAgent

Scenario: Testing Mock Repository 
Given that 
When  
Then 