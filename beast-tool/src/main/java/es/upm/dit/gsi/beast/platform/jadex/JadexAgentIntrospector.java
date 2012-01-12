package es.upm.dit.gsi.beast.platform.jadex;

import jadex.bdi.runtime.IBDIInternalAccess;
import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.IPlan;
import jadex.bdi.runtime.Plan;
import jadex.bridge.IComponentStep;
import jadex.bridge.IExternalAccess;
import jadex.bridge.IInternalAccess;
import jadex.commons.future.IFuture;
import jadex.commons.future.ThreadSuspendable;
import es.upm.dit.gsi.beast.platform.AgentIntrospector;
import es.upm.dit.gsi.beast.platform.Connector;

/**
 * To get information about our jadex agents.
 * 
 * @author Jorge Solitario
 */
public class JadexAgentIntrospector implements AgentIntrospector {


    private Object belief_value;
    private IPlan[] plans;
    private IGoal[] goals;

    private static JadexAgentIntrospector INSTANCE = new JadexAgentIntrospector();

    private JadexAgentIntrospector() {
    }

    public static JadexAgentIntrospector getInstance() {
        return INSTANCE;
    }

    /**
     * This method takes the value of an agent's belief through its external
     * access
     * 
     * @param agent_name
     *            The name of the agent
     * @param belief_name
     *            The name of the belief inside agent's adf
     * @param connector
     *            The connector to get the external access
     * @return belief_value The value of the requested belief
     */
    public Object getBeliefValue(String agent_name, final String belief_name,
            Connector connector) {

        ((IExternalAccess) connector.getAgentsExternalAccess(agent_name))
                .scheduleStep(new IComponentStep<Integer>() {

                    public IFuture<Integer> execute(IInternalAccess ia) {
                        IBDIInternalAccess bia = (IBDIInternalAccess) ia;
                        belief_value = bia.getBeliefbase()
                                .getBelief(belief_name).getFact();
                        return null;
                    }
                }).get(new ThreadSuspendable());
        return belief_value;
    }

    /**
     * This method changes the value of an agent's belief through its external
     * access
     * 
     * @param agent_name
     *            The name of the agent to change a belief
     * @param belief_name
     *            The name of the belief to change
     * @param new_value
     *            The new value of the belief to be changed
     * @param connector
     *            The connector to get the external access
     */
    public void setBeliefValue(String agent_name, final String belief_name,
            final Object new_value, Connector connector) {

        ((IExternalAccess) connector.getAgentsExternalAccess(agent_name))
                .scheduleStep(new IComponentStep<Integer>() {

                    public IFuture<Integer> execute(IInternalAccess ia) {
                        IBDIInternalAccess bia = (IBDIInternalAccess) ia;
                        bia.getBeliefbase().getBelief(belief_name)
                                .setFact(new_value);
                        return null;
                    }
                }).get(new ThreadSuspendable());
    }

    /**
     * This method prints plan information of an agent through its external
     * access. It can be used to check the correct behaviour of the agent.
     * 
     * @param agent_name
     *            The name of the agent
     * @param connector
     *            The connector to get the external access
     * @return plans the IPlan[] with all the information, so the tester can
     *         look for information
     */
    public IPlan[] getAgentPlans(final String agent_name, Connector connector) {
        ((IExternalAccess) connector.getAgentsExternalAccess(agent_name))
                .scheduleStep(new IComponentStep<Plan>() {

                    public IFuture<Plan> execute(IInternalAccess ia) {

                        IBDIInternalAccess bia = (IBDIInternalAccess) ia;
                        plans = bia.getPlanbase().getPlans();
                        return null;
                    }
                }).get(new ThreadSuspendable());

        return plans;
    }

    /**
     * This method prints goal information of an agent through its external
     * access. It can be used to check the correct behaviour of the agent.
     * 
     * @param agent_name
     *            The name of the agent
     * @param connector
     *            The connector to get the external access
     * @return goals the IGoal[] with all the information, so the tester can
     *         look for information
     */
    public IGoal[] getAgentGoals(final String agent_name, Connector connector) {
        ((IExternalAccess) connector.getAgentsExternalAccess(agent_name))
                .scheduleStep(new IComponentStep<Plan>() {

                    public IFuture<Plan> execute(IInternalAccess ia) {
                        IBDIInternalAccess bia = (IBDIInternalAccess) ia;

                        goals = bia.getGoalbase().getGoals();
                        return null;
                    }
                }).get(new ThreadSuspendable());

        return goals;
    }

}
