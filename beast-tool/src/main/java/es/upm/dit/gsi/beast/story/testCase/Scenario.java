package es.upm.dit.gsi.beast.story.testCase;

import java.util.ArrayList;
import java.util.logging.Logger;

import es.upm.dit.gsi.beast.platform.Connector;
import es.upm.dit.gsi.beast.platform.Messenger;
import es.upm.dit.gsi.beast.platform.PlatformSelector;

/**
 * Launches the platform and creates its agents. It is related with the GIVEN
 * part of BDD.
 * 
 * @author Jorge Solitario
 */
public abstract class Scenario {

    protected Logger logger;

    abstract public void startAgents();

    Connector connector;
    String platform;
    Messenger messenger;

    /**
     * Main constructor of the class, launches the platform
     */
    public void startPlatform(String platform, Logger logger) {
        this.logger = logger;
        logger.finest(">> startPlatform");
        this.platform = platform;
        connector = PlatformSelector.getConnector(platform, logger);
        logger.info("Platform " + platform + " is not launched yet.");
        connector.launchPlatform();
        logger.info("Platform " + platform + " launched.");
        messenger = PlatformSelector.getMessenger(platform);
        startAgents();
    }

    /**
     * 
     * @return the name of the used agent platform
     */
    public String getPlatform() {
        return this.platform;
    }
    
    /**
     * @return the platform connector
     */
    public Connector getConnector() {
        return this.connector;
    }

    /**
     * Creates a real agent in the platform
     * 
     * @param agent_name
     *            The name that the agent is gonna have in the platform
     * @param path
     *            The path of the description of the agent
     */
    public void startAgent(String agent_name, String path) {
        connector.createAgent(agent_name, path);
    }
    
    
    
    /**
     * Creates a real agent in the platform in a given container
     * 
     * @param agent_name The name of the agent
     * @param path The path of the Agent
     * @param containerName The name of the container, if it does not exist, beast-tool creates it.
     * @param arguments For the agent
     */
    public void startAgent(String agent_name, String path, String containerName, Object[] arguments) {
        connector.createAgent(agent_name, path, containerName, arguments);
    }

    /**
     * It sends one message of requested type to an agent
     * 
     * @param agent_name
     *            The name of the agent that receives the message
     * @param msgtype
     *            The type of the message (SFipa.INFORM - SFipa.REQUEST)
     * @param message_content
     *            The content of the message
     */
    public void sendMessageToAgent(String agent_name, String msgtype,
            Object message_content) {

        String[] names = new String[1];
        names[0] = agent_name;
        messenger.sendMessageToAgents(names, msgtype, message_content,
                connector);
    }

    /**
     * The same as above, but this method sends the same message to many agents.
     * 
     * @param agent_name
     *            The name of the agent that receives the message
     * @param msgtype
     *            The type of the message (SFipa.INFORM - SFipa.REQUEST)
     * @param message_content
     *            The content of the message
     */
    public void sendMessageToAgents(String[] agent_name, String msgtype,
            Object message_content) {

        messenger.sendMessageToAgents(agent_name, msgtype, message_content,
                connector);
    }

    /**
     * It sends one message of requested type to an agent, including some extra
     * parameters in the message event, such as ontology or language.
     * 
     * @param agent_name
     *            The name of the agent that receives the message
     * @param msgtype
     *            The type of the message (SFipa.INFORM - SFipa.REQUEST)
     * @param message_content
     *            The content of the message
     * @param properties
     *            to add to the message
     */
    public void sendMessageToAgentsWithExtraProperties(String agent_name,
            String msgtype, Object message_content, ArrayList<Object> properties) {

        String[] names = new String[1];
        names[0] = agent_name;
        messenger.sendMessageToAgentsWithExtraProperties(names, msgtype,
                message_content, properties, connector);
    }

}
