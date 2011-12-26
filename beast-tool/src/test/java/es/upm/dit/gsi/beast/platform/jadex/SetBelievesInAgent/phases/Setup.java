package es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent.phases;

/**  
 * This is the class that must create the Setup.
 * It is related with the WHEN part.
 *  
 * In setStates method the following methods must be used
 *    super.setBeliefValue (agent_name, belief_name, new_value )
 *    super.sendMessageToAgent(agent_name, msgtype, message_content)
 *    super.getAgentPlans(agent_name)
 *    super.getAgentGoals(agent_name )
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Setup extends es.upm.dit.gsi.beast.story.phases.Setup {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   tester wants to set a belief inside a Jadex agent
   * 
   */
    public void setStates() {
    // TODO implement this method to represent the @When part of the test in Java code.
    logger.warning("Implement setStates() method in es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent.test.SetupSetBelievesInAgent.java -> Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool");
    
    //EXAMPLE: setBeliefValue("Steve", "age", 21);
  }

}
