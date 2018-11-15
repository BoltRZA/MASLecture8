package MASLecture8;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;

public class AgentReceiver extends Agent {

    @Override
    protected void setup() {
        super.setup();
        AID topic = registerTopic();

        addBehaviour(new ReceiveMsgFromTopic(topic));

    }

    private  AID registerTopic(){
        TopicManagementHelper topicManagementHelper = null;
        AID topic = null;

        try{
            topicManagementHelper = (TopicManagementHelper)getHelper(TopicManagementHelper.SERVICE_NAME);
            topic = topicManagementHelper.createTopic("JADE");
            topicManagementHelper.register(topic);
        }
        catch (ServiceException e){
            e.printStackTrace();
        }
        return topic;
    }

}
