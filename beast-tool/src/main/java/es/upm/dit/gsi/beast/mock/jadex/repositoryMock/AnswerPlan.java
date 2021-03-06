package es.upm.dit.gsi.beast.mock.jadex.repositoryMock;

import jadex.base.fipa.SFipa;
import jadex.bdi.runtime.IMessageEvent;
import jadex.bdi.runtime.Plan;
import jadex.bridge.IComponentIdentifier;

import java.util.logging.Logger;

import es.upm.dit.gsi.beast.mock.common.AgentBehaviour;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.mock.jadex.repositoryMock.AnswerPlan.java
 * 
 * Plan to answer arriving messages
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * 
 * @author Jorge Solitario
 * @version 0.1
 * 
 */
public class AnswerPlan extends Plan {

    /** Serial version UID of the serializable class BehaviourPlan. */
    private static final long serialVersionUID = 4476473302410302L;

    private Logger logger = Logger.getLogger(AnswerPlan.class.getName());

    public void body() {
        IMessageEvent actReq = (IMessageEvent) getReason();

        int count = (Integer) getBeliefbase().getBelief("received_count")
                .getFact();
        count++;
        getBeliefbase().getBelief("received_count").setFact(count);

        String type = (String) actReq.getParameter("performative").getValue();
        Object content = actReq.getParameter(SFipa.CONTENT).getValue();
        String agent_name = (String) ((IComponentIdentifier) actReq
                .getParameter(SFipa.SENDER).getValue()).getLocalName();

        String answer = (String) ((AgentBehaviour) getBeliefbase().getBelief(
                "agent_behaviour").getFact()).processMessage(type, agent_name,
                content);
        if (answer == null)
            answer = "Unknown required action";
        logger.info("Answer: " + answer);

        IMessageEvent msgResp = getEventbase().createReply(actReq,
                "send_inform");
        msgResp.getParameter(SFipa.CONTENT).setValue(answer);
        sendMessage(msgResp);
    }
}
