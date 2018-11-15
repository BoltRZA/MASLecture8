package MASLecture8;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceiveMsgFromTopic extends Behaviour {
    AID topic= null;

    public ReceiveMsgFromTopic(AID topic) {

        this.topic = topic;
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.MatchTopic(topic);
        ACLMessage msg = getAgent().receive(mt);
        if (msg!=null){
            System.out.println(getAgent().getLocalName()+" I have received message");
            System.out.println(msg.getContent());
        }
        else {
            block();
        }
    }

    public boolean done() {

        return false;
    }
}
