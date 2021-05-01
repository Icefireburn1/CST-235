package business;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import beans.Order;

@Alternative
@Stateless
@Local(OrderBusinessInterface.class)
public class OrdersBusinessService implements OrderBusinessInterface {

	List<Order> orders = new ArrayList<Order>();
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;
	
	@Override
	public void test() {
		System.out.println("Hello from the test method v1");

	}
	
	public OrdersBusinessService() {
		orders.add(new Order("0000000000", "This is product 1", (float)1.00, 1));
		orders.add(new Order("0000000001", "This is product 2", (float)15.00, 5));
		orders.add(new Order("0000000002", "This is product 3", (float)125.00, 7));
		orders.add(new Order("0000000003", "This is product 4", (float)20.00, 22));
		orders.add(new Order("0000000004", "This is product 5", (float)55.00, 64));
		orders.add(new Order("0000000005", "This is product 6", (float)33.00, 32));
		orders.add(new Order("0000000006", "This is product 7", (float)26.00, 12));
		orders.add(new Order("0000000007", "This is product 8", (float)126.23, 23));
		orders.add(new Order("0000000008", "This is product 9", (float)16.23, 85));
		orders.add(new Order("0000000009", "This is product 10", (float)1.11, 53));
		orders.add(new Order("0000000010", "This is product 11", (float)77.22, 2));
		orders.add(new Order("0000000011", "This is product 12", (float)243.22, 7));
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

	@Override
	public void sendOrder(Order order) {
//		// V1
//		// Send a Message for an Order
//		try 
//		{
//			Connection connection = connectionFactory.createConnection();
//			Session  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//			MessageProducer messageProducer = session.createProducer(queue);
//			TextMessage message1 = session.createTextMessage();
//			//message1.set
//			//messageProducer.send(message1);
//			connection.close();
//		} 
//		catch (JMSException e) 
//		{
//			e.printStackTrace();
//		}		
		// Our SQL statement
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
	        String REGISTER_ORDER = "INSERT INTO testapp.orders" +
	                "  (order_no, product_name, price, quantity) VALUES " +
	                " (?, ?, ?, ?);";
	        
	        PreparedStatement ps = conn.prepareStatement(REGISTER_ORDER);
	        ps.setString(1, order.getOrderNo());
	        ps.setString(2, order.getProductName());
	        ps.setFloat(3, order.getPrice());
	        ps.setInt(4, order.getQuantity());
	        
	        
	        
	        ps.executeUpdate();
	        
	        orders.add(order);

			//System.out.println("Insert Success!!");
		} catch (SQLException e) {
			//System.out.println("Insert Failure!!");
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
