package es.upm.dit.gsi.beast.platform;


import java.util.ArrayList;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.platform.Messenger.java
 * 
 * Abstract Class that defines method
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author jjsolitario
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @version 0.1
 * 
 */
public interface Messenger {

    /**
     * Method to send one message to many agents
     * 
     * @param agent_name
     *            The Array with the Receivers name
     * @param msgtype
     *            SFipa.Inform or SFipa.Request
     * @param message_content
     *            The content of the message
     * @param connector
     *            The connector to the platform
     */
    public void sendMessageToAgents(String[] agent_name, String msgtype,
            Object message_content, Connector connector);

    /**
     * Method to send one message with extra properties to many agents
     * 
     * @param agent_name
     *            The Array with the Receivers name
     * @param msgtype
     *            SFipa.Inform or SFipa.Request
     * @param message_content
     *            The content of the message
     * @param properties
     *            The properties to add to the message
     * @param connector
     *            The connector to the platform
     */
    public void sendMessageToAgentsWithExtraProperties(String[] agent_name,
            String msgtype, Object message_content,
            ArrayList<Object> properties, Connector connector);
}
