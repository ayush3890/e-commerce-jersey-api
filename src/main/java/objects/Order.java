package objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import java.sql.Timestamp;

public class Order {
	private UUID OrderId;
	private UUID OrderUserId;
	private UUID OrderSellerId;
	private float OrderAmount;

	private String OrderEmail;
	private Timestamp OrderDate;
	private int OrderStatus;
	private String OrderTrackingNumber;
//	private String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
	private ArrayList<Item> OrtderItems;

	public UUID getOrderId() {
		return OrderId;
	}

	public void setOrderId() {
		OrderId = UUID.randomUUID();
	}

	public void setOrderId(UUID orderID) {
		OrderId = orderID;
	}

	public UUID getOrderUserId() {
		return OrderUserId;
	}

	public void setOrderUserId(UUID orderUserId) {
		OrderUserId = orderUserId;
	}

	public float getOrderAmount() {
		return OrderAmount;
	}

	public void setOrderAmount(float orderAmount) {
		OrderAmount = orderAmount;
	}



	public String getOrderEmail() {
		return OrderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		OrderEmail = orderEmail;
	}

	public Timestamp getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate() {
		OrderDate = new Timestamp(System.currentTimeMillis());
	}

	public void setOrderDate(String time) {
		OrderDate = Timestamp.valueOf(time);
	}

	public int getOrderStatus() {
		return OrderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		OrderStatus = orderStatus;
	}

	public String getOrderTrackingNumber() {
		return OrderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		OrderTrackingNumber = orderTrackingNumber;
	}

	public ArrayList<Item> getOrtderItems() {
		return OrtderItems;
	}

	public void setOrtderItems(ArrayList<Item> ortderItems) {
		OrtderItems = ortderItems;
	}

	public UUID getOrderSellerId() {
		return OrderSellerId;
	}

	public void setOrderSellerId(UUID orderSellerId) {
		OrderSellerId = orderSellerId;
	}

}
