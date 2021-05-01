package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Order;
import beans.User;
import business.MyTimerService;
import business.OrderBusinessInterface;

@ManagedBean
@ViewScoped
public class FormController {

	@Inject
	OrderBusinessInterface services;
	
	@EJB
	MyTimerService timer;
	
	public String onLogoff() {
		// Invalidate the Session to clear the security token
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			
		// Redirect to a protected page (so we get a full HTTP Request) to get Login Page
		return "TestResponse.xhtml?faces-redirect=true";
	}
	
	public String onFlash(User user) {
//		FacesContext.getCurrentInstance().getAttributes().put("user", user);
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
//		Map<String, Object> sessionMap = ec.getSessionMap();
//		
//		sessionMap.put("firstname", user.getFirstName());
//		sessionMap.put("lastname", user.getLastName());
//		
		return "TestResponse2.xhtml?faces-redirect=true";
	}
	
	public OrderBusinessInterface getService() {
		return services;
	}
	
	public String onSendOrder() {
		services.sendOrder(new Order("0000000000", "Test", (float)1.00, 1));
		return "OrderResponse.xhtml?faces-redirect=true";
	}
	
	private void getAllOrders() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM testapp.orders";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String PRODUCT_NAME = rs.getString("PRODUCT_NAME");
				Float PRICE = rs.getFloat("PRICE");
				//System.out.println(ID + "# " + PRODUCT_NAME + " $" + PRICE);
			}
			rs.close();
			//System.out.println("Success!!");
		} catch (SQLException e) {
			//System.out.println("Failure!!");
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
	
	public void insertOrder() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO testapp.ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES('001122334455', 'This was inserted new', 25.00, 100)";
			statement.executeQuery(sql);

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