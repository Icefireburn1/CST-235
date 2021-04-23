package business;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: OrderMessageService
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/Order"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/Order")
public class OrderMessageService implements MessageListener {
	
	
    /**
     * Default constructor. 
     */
    public OrderMessageService() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	
    	// V2
        if (message instanceof ObjectMessage) {
        	ObjectMessage text = (ObjectMessage)message;
        	try {
				System.out.println((ObjectMessage)text.getObject());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return;
        }
    	
    	
    	// V1
        if (message instanceof TextMessage) {
        	TextMessage text = (TextMessage)message;
        	try {
				System.out.println(text.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return;
        }
        
    }

}
