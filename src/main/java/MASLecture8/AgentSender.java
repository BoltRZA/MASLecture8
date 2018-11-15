package MASLecture8;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.messaging.TopicManagementFEService;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;

public class AgentSender extends Agent {
    @Override
    protected void setup() {
        super.setup();
        AID topic = createTopic();

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        Behaviour b = (Behaviour) new OneShotBehaviour() {
           AID topic = null;
           Behaviour setTopic(AID t){
               topic = t;
               return this;
           }
            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent("It's a message from topic");
                msg.addReceiver(topic);
                getAgent().send(msg);
            }
        }.setTopic(topic); // Создаем экземпляр класса ваншот, вызываем у него метод сеттопик,
        // результатом которого будет экземпляр созданного класса
        addBehaviour(b);
    }
    private AID createTopic() {
        AID topic = null;
        TopicManagementHelper topicManagementHelper = null;
        try {
            topicManagementHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
            topic = topicManagementHelper.createTopic("JADE");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return topic;
    }
}
