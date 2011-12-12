package es.upm.dit.gsi.beast.story.testCase;

import jadex.commons.Tuple;

import java.util.ArrayList;

import es.upm.dit.gsi.beast.platform.jadex.JadexConnector;
import es.upm.dit.gsi.beast.platform.jadex.JadexMessenger;


/**
 * Lauches the platform and creates its agents.
 * It is related with the GIVEN part of BDD. 
 * 
 * @author Jorge Solitario
 */
public abstract class Scenario {
	
	abstract public void startAgents();
	JadexConnector jadexConnector;
	
	
	/**
	 *  Main constructor of the class, launches the platform
	 */
	public void startPlatform(){
		jadexConnector = new JadexConnector();
		jadexConnector.launchPlatform();
		startAgents();
	}
	
	
	/**
	 * Creates a real agent in the platform
	 * 
	 * @param name The name that the agent is gonna have in the platform
	 * @param path The path of the description (xml) of the agent 
	 */
	protected void startAgent(String agent_name, String path) {
		jadexConnector.createAgent(agent_name,path);
	}

	
	/**
	 * It sends one message of requested type to an agent
	 * 
	 * @param agent_name The name of the agent that receives the message
	 * @param message_content The content of the message
	 */
	public void sendMessageToAgent(String agent_name, String msgtype, Object message_content) {
				
		String[] names = new String[1];
		names[0] = agent_name;
		JadexMessenger.sendMessageToAgents(names, msgtype, message_content, jadexConnector);
	}
	

	
	/**
	 * The same as above, but this method sends the same message to many agents.
	 * 
	 * @param agent_name The name of the agent that receives the message
	 * @param message_content The content of the message
	 * @return The content of sended message
	 */
	public void sendMessageToAgents(String[] agent_name, String msgtype, Object message_content) {
		
		JadexMessenger.sendMessageToAgents(agent_name, msgtype, message_content, jadexConnector);	
	}

	
	/**
	 * It sends one message of requested type to an agent, including some extra
	 * parameters in the message event, such as ontology or language.
	 * 
	 * @param agent_name
	 * @param msgtype
	 * @param message_content
	 * @param properties
	 */
	public void sendMessageToAgentsWithExtraProperties(String agent_name, String msgtype, Object message_content, ArrayList<Tuple> properties) {
		
		String[] names = new String[1];
		names[0] = agent_name;
		JadexMessenger.sendMessageToAgentsWithExtraProperties(names, msgtype, message_content, properties, jadexConnector);	
	}
	
}
