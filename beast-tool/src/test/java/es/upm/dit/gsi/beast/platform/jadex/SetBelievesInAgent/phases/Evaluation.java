package es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent.phases;


/**  
 * This is the class that must create the Evaluation.
 * It is related with the THEN part.
 *  
 * In checkStates method the following method must be used
 *    checkAgentsBeliefEquealsTo(agent_name,belief_name,expected_belief_value)
 * 
 * @author es.upm.dit.gsi.beast
 */

public class Evaluation extends es.upm.dit.gsi.beast.story.phases.Evaluation {
/**  
   * Here the description given by the client must be written,
   * which is: 
   *  
   *   the Jadex belief is set
   * 
   */
    public void checkStates() {
   
        checkAgentsBeliefEquealsTo("Tester", "message_count", 3);

  }

}
