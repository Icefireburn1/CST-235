package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import beans.Order;

@Stateless
@Local(OrderBusinessInterface.class)
@Alternative
public class OrdersBusinessService2 implements OrderBusinessInterface {

	List<Order> orders = new ArrayList<Order>();
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;
	
	@Override
	public void test() {
		System.out.println("Hello from the test method v2");

	}

	public OrdersBusinessService2() {
		orders.add(new Order("000000000a", "This is taco 1", (float)1.00, 1));
		orders.add(new Order("000000000b", "This is taco 2", (float)15.00, 5));
		orders.add(new Order("000000000c", "This is taco 3", (float)125.00, 7));
		orders.add(new Order("000000000d", "This is taco 4", (float)20.00, 22));
		orders.add(new Order("000000000e", "This is taco 5", (float)55.00, 64));
		orders.add(new Order("000000000f", "This is taco 6", (float)33.00, 32));
		orders.add(new Order("000000000g", "This is taco 7", (float)26.00, 12));
		orders.add(new Order("000000000h", "This is taco 8", (float)126.23, 23));
		orders.add(new Order("000000000i", "This is taco 9", (float)16.23, 85));
		orders.add(new Order("000000000j", "This is taco 10", (float)1.11, 53));
		orders.add(new Order("000000001k", "This is taco 11", (float)77.22, 2));
		orders.add(new Order("000000001l", "This is taco 12", (float)243.22, 7));
	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		// TODO Auto-generated method stub
		this.orders = orders;
	}

	@SuppressWarnings("null")
	@Override
	public void sendOrder(Order order) {
		// Send a Message for an Order
		try 
		{
			Connection connection = connectionFactory.createConnection();
			Session  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			ObjectMessage message2 = null;
			message2.setObject((Serializable) order);
			messageProducer.send(message2);
			connection.close();
		} 
		catch (JMSException e) 
		{
			e.printStackTrace();
		}		
	}
}
