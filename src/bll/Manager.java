package bll;

import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.*;
import model.*;

public class Manager {
	private ArrayList<Customer> cl;
	private ArrayList<Order> com;
	private ArrayList<Product> prod;
	private CustomerDAO cDAO;
	private OrderDAO oDAO;
	private ProductDAO pDAO;

	public Manager() {
		cDAO = new CustomerDAO();
		this.cl = (ArrayList<Customer>) cDAO.findAll();
		oDAO = new OrderDAO();
		this.com = (ArrayList<Order>) oDAO.findAll();
		pDAO = new ProductDAO();
		this.prod = (ArrayList<Product>) pDAO.findAll();

	}

	public ArrayList<Customer> getCustomers() {
		return this.cl;
	}

	public ArrayList<Product> getProducts() {
		return this.prod;
	}

	public ArrayList<Order> getOrders(){
		return this.com;
	}

	public void editCustomer(int id, String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		for (Customer c : cl) {
			if (c.getId() == id) {
				c.setName(name);
				
				cDAO.update(c);
			}
		}
	}

	public void deleteCustomer(int id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		for (Customer c : cl) {
			if (c.getId() == id) {
				c.setIsDeleted(true);
				cDAO.update(c);
			}
		}
	}

	public void addCustomer(String name)
			throws SQLException, SecurityException, IllegalArgumentException, InstantiationException,
			IllegalAccessException, IntrospectionException, InvocationTargetException {
		int id=0;
		if(cl.size()>0)
			id = cl.get(cl.size() - 1).getId() + 1;
		Customer c = new Customer(id, name, false);
		cl.add(c);
		cDAO = new CustomerDAO();
		try {
			cDAO.insertObject(c);
		} catch (SQLException| SecurityException| IllegalArgumentException|InstantiationException|
				IllegalAccessException| IntrospectionException| InvocationTargetException e) {
			
			e.printStackTrace();
		}
	}
	
	public void editProduct(int id, String name, int quantity, double price) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		for (Product p : prod) {
			if (p.getId() == id) {
				p.setName(name);
				p.setQuantity(quantity);
				p.setPrice(price);
				pDAO.update(p);
			}
		}
	}

	public void deleteProduct(int id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		for (Product p : prod) {
			if (p.getId() == id) {
				p.setIsDeleted(true);
				pDAO.update(p);
			}
		}
	}

	public void addProduct(String name, int quantity, double d)
			throws SQLException, SecurityException, IllegalArgumentException, InstantiationException,
			IllegalAccessException, IntrospectionException, InvocationTargetException {
		int id=0;
		if(prod.size()>0)
			id = prod.get(prod.size() - 1).getId() + 1;
		Product p = new Product(id, name, quantity, false, d);
		prod.add(p);
		pDAO = new ProductDAO();
		try {
			pDAO.insertObject(p);
		} catch (SQLException| SecurityException| IllegalArgumentException|InstantiationException|
				IllegalAccessException| IntrospectionException| InvocationTargetException e) {
		
			e.printStackTrace();
		}
	}

	
	public void addOrder(int customerID, int productID) {
		int canPlaceOrder = 1;
		Product prod1 = new Product();
		for (Customer c : cl)
			if (c.getId() == customerID && c.getIsDeleted() == true)
				canPlaceOrder = 0;
		for (Product p : prod)
			if (p.getId() == productID) {
				if (p.getIsDeleted() == true || p.getQuantity() == 0)
					canPlaceOrder = 0;
				prod1 = p;
			}
		if (canPlaceOrder == 1) {
			prod1.setQuantity(prod1.getQuantity() - 1);
			int id=0;
			if(com.size()>0)
				id = com.get(com.size() - 1).getId() + 1;
			Order o = new Order(id, customerID, productID, false);
			com.add(o);
			oDAO = new OrderDAO();
			try {
				oDAO.insertObject(o);
			} catch (SQLException| SecurityException| IllegalArgumentException|InstantiationException|
					IllegalAccessException| IntrospectionException| InvocationTargetException e) {
				
				e.printStackTrace();
			}
		}
	}

	public void deleteOrder(int id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		for (Order o : com) {
			if (o.getId() == id) {
				o.setIsDeleted(true);
				oDAO.update(o);
			}
		}
	}

	

}