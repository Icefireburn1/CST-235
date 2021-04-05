package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class Orders {

	List<Order> orders = new ArrayList<Order>();
	
	public Orders() {
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
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
