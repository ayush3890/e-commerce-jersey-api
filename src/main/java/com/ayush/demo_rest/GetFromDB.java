package com.ayush.demo_rest;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import com.mysql.cj.xdevapi.Result;

import objects.Address;
import objects.Invoice;
import objects.Invoice_Item;
import objects.Item;
import objects.Order;
import objects.Product;
import objects.Seller;

public class GetFromDB {
	private ArrayList<about> list;
	private Connection con;
	private Statement stmt;

	public GetFromDB() {
		list = new ArrayList<about>();

		try {
			// 1. Get connection with database;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ekart", "root", "ayush3890");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<about> getAll() {
		list = new ArrayList<about>();
		String selectquery = "select * from about";
		ResultSet result;
		try {
			// 2. Create a statement;
			stmt = con.createStatement();
			// 3. Execute sql query;
			System.out.println(stmt);
			result = stmt.executeQuery(selectquery);
			// 4. Process the result set;
			while (result.next()) {
				about a = new about();
				a.setName(result.getString("Name"));
				a.setPhno(result.getInt("Phone_No"));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return list;
	}

	public about getWithPhNo(int PhNo) {
		String selectquery = "select * from about where Phone_No=" + PhNo;
		ResultSet result;
		try {
			// 2. Create a statement;
			stmt = con.createStatement();
			// 3. Execute sql query;
			result = stmt.executeQuery(selectquery);
			// 4. Process the result set;
			about a = new about();
			a.setName(result.getString("Name"));
			a.setPhno(result.getInt("Phone_No"));
			System.out.println(a);
			return a;
		} catch (SQLException e) {
//			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<about> create(about a1) {
		String insertQuery = "insert into about values(?, ?);";
		try {
			// 2. Create a statement;
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, a1.getName());
			stmt.setInt(2, a1.getPhno());
			// 3. Execute sql query;
			stmt.executeUpdate();

		} catch (SQLException e) {
		}
		return getAll();
	}

	public ArrayList<about> update(about a1) {
		String insertQuery = "update about set name=? where Phone_No=?;";
		try {
			// 2. Create a statement;
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, a1.getName());
			stmt.setInt(2, a1.getPhno());
			// 3. Execute sql query;
			int res = stmt.executeUpdate();
			System.out.println("====>" + stmt);
			System.out.println(res);

			return getAll();
		} catch (SQLException e) {
			System.out.println(e);
			return new ArrayList<about>();
		}
	}

	public about delete(about a1) {
		String insertQuery = "delete from about where Name=? AND Phone_No=?;";
		try {
			// 2. Create a statement;
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, a1.getName());
			stmt.setInt(2, a1.getPhno());
			// 3. Execute sql query;
			int res = stmt.executeUpdate();
			System.out.println("====>" + stmt);
			System.out.println(res);

			return a1;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public Seller seller_with_email_or_phone(String email, String phno) {
		String insertQuery = "select * from seller where Email = ? OR PhoneNo = ?;";
		try {
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, email);
			stmt.setString(2, phno);
			ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			} else {
				Seller seller = new Seller();
				seller.setName(result.getString(1));
				seller.setEmail(result.getString(2));
				seller.setPassword(result.getString(3));
				seller.setPhno(result.getString(4));
				return seller;
			}
		} catch (SQLException e) {
			return null;
		}
	}

	public void create_seller(Seller new_seller) {
		System.out.println(new_seller);
		String insertQuery = "insert into `seller` values(?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, new_seller.getSellerId().toString());
			stmt.setString(2, new_seller.getName());
			stmt.setString(3, new_seller.getEmail());
			stmt.setString(4, new_seller.getPassword());
			stmt.setString(5, new_seller.getPhno());
			boolean res = stmt.execute();
			System.out.println(res);
		} catch (SQLException e) {
		}
	}

	public void createOrder(Order order) {
		ArrayList<Item> OrderItems = order.getOrtderItems();
		for (Item each : OrderItems) {
			this.createItem(each, order.getOrderId());
		}
		String insertQuery = "insert into `order` values(?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			// 2. Create a statement;
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, order.getOrderId().toString());
			stmt.setString(2, order.getOrderUserId().toString());
			stmt.setString(3, order.getOrderSellerId().toString());
			stmt.setFloat(4, order.getOrderAmount());

//			stmt.setString(5, order.getOrderShipName());
//			stmt.setString(6, order.getOrderShipAddress1());
//			stmt.setString(7, order.getOrderShipAddress2());
//			stmt.setString(8, order.getOrderCity());
//			stmt.setString(9, order.getOrderState());
//			stmt.setString(10, order.getOrderCountry());
//			stmt.setString(11, order.getOrderZip());

			stmt.setString(5, order.getOrderEmail());
			stmt.setTimestamp(6, order.getOrderDate());
			stmt.setInt(7, order.getOrderStatus());
			stmt.setString(8, order.getOrderTrackingNumber());

			System.out.println(stmt);
			// 3. Execute sql query;
			boolean res = stmt.execute();
//			System.out.println("===>" + res);
		} catch (SQLException e) {
		}
	}

	public Order order_with_orderid(UUID orderID) {
		String insertQuery = "select * from order where OrderID = ?;";
		try {
			// 2. Create a statement;
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, orderID.toString());
			// 3. Execute sql query;
			ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			} else {
				Order order = new Order();
				order.setOrderId(UUID.fromString(result.getString(1)));
				order.setOrderUserId(UUID.fromString(result.getString(2)));
				order.setOrderSellerId(UUID.fromString(result.getString(3)));
				order.setOrderAmount(result.getFloat(4));

//				order.setOrderShipName(result.getString(5));
//				order.setOrderShipAddress1(result.getString(6));
//				order.setOrderShipAddress2(result.getString(7));
//				order.setOrderCity(result.getString(8));
//				order.setOrderState(result.getString(9));
//				order.setOrderCountry(result.getString(10));
//				order.setOrderZip(result.getString(11));

				order.setOrderEmail(result.getString(5));
				order.setOrderDate(result.getString(6));
				order.setOrderStatus(result.getInt(7));
				order.setOrderTrackingNumber(result.getString(8));
				return order;
			}
		} catch (SQLException e) {
			return null;
		}
	}

	private void createItem(Item each, UUID orderId) {
		String insertQuery = "insert into `item` values(?, ?, ?, ?);";
		try {
			// 2. Create a statement;
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, orderId.toString());
			stmt.setString(2, each.getProductID().toString());
			stmt.setInt(3, each.getQtyRequired());
			stmt.setInt(4, each.getQtyDispatched());
			// 3. Execute sql query;
			boolean res = stmt.execute();
		} catch (SQLException e) {
		}
	}

	public void createInvoice(Invoice invoice) {
		ArrayList<Invoice_Item> InvoiceItems = invoice.getInvoiceItems();
		for (Invoice_Item each : InvoiceItems) {
			this.createInvoiceItem(each, invoice.getInvoiceID());
		}
		String insertQuery = "insert into `invoice` values(?, ?, ?, ?);";
		try {
			// 2. Create a statement;
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, invoice.getInvoiceID().toString());
			stmt.setString(2, invoice.getSellerID().toString());
			stmt.setString(3, invoice.getBuyerID().toString());
			stmt.setString(4, invoice.getOrderID().toString());
			System.out.println(stmt);
			// 3. Execute sql query;
			boolean res = stmt.execute();
//			System.out.println("===>" + res);
		} catch (SQLException e) {
		}
	}

	private void createInvoiceItem(Invoice_Item each, UUID invoiceID) {
		// TODO Auto-generated method stub
		String insertQuery = "insert into `invoice_item` values(?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = con.prepareStatement(insertQuery);

			stmt.setString(1, invoiceID.toString());
			stmt.setString(2, each.getProductID().toString());
			stmt.setInt(3, each.getQtyRequired());
			stmt.setInt(4, each.getQtyDispatched());
			stmt.setString(5, each.getAddressID().toString());

			boolean res = stmt.execute();
		} catch (SQLException e) {
		}
	}

	public void crete_product(Product new_product) {
		String insertQuery = "INSERT INTO `product` VALUES (?, ?, ?, ?, ?, ?);";
		try {
			// 2. Create a statement;
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, new_product.getProductID().toString());
			stmt.setString(2, new_product.getCreaterSellerID().toString());
			stmt.setString(3, new_product.getName());
			stmt.setString(4, new_product.getBrandName());
			stmt.setInt(5, new_product.getPrice());
			stmt.setInt(6, new_product.getDiscount());
			// 3. Execute sql query;
			boolean res = stmt.execute();
		} catch (SQLException e) {
		}
	}

	public void acceptOrder(UUID orderID, String status) {
		String insertQuery = "update `order` set OrderStatus=? where OrderID = ?;";
		try {
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, status);
			stmt.setString(2, orderID.toString());
			System.out.println(stmt);
			stmt.executeUpdate();
		} catch (Exception E) {

		}
	}

	public void create_address(Address address) {
		String insertQuery = "INSERT INTO `address` VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, address.getArrdressID().toString());
			stmt.setString(2, address.getOrderShipName());
			stmt.setString(3, address.getOrderShipAddress1());
			stmt.setString(4, address.getOrderShipAddress2());
			stmt.setString(5, address.getOrderCity());
			stmt.setString(6, address.getOrderState());
			stmt.setString(7, address.getOrderCountry());
			stmt.setString(8, address.getOrderZip());

			boolean res = stmt.execute();
		} catch (SQLException e) {
		}
	}

	public Address get_address(UUID addressID) {
		String insertQuery = "select * from address where ArrdressID = ?;";
		try {
			// 2. Create a statement;
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, addressID.toString());
			// 3. Execute sql query;
			ResultSet result = stmt.executeQuery();
			if (!result.next()) {
				return null;
			} else {
				Address address = new Address();
				address.setArrdressID(addressID);
				address.setOrderShipName(result.getString(1));
				address.setOrderShipAddress1(result.getString(2));
				address.setOrderShipAddress2(result.getString(3));
				address.setOrderCity(result.getString(4));
				address.setOrderState(result.getString(5));
				address.setOrderCountry(result.getString(6));
				address.setOrderZip(result.getString(7));
				return address;
			}
		} catch (SQLException e) {
			return null;
		}
	}

}
