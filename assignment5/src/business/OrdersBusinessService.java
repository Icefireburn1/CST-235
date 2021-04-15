package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

@Alternative
@Stateless
@Local(OrderBusinessInterface.class)
public class OrdersBusinessService implements OrderBusinessInterface {

	List<Order> orders = new ArrayList<Order>();
	
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

}
