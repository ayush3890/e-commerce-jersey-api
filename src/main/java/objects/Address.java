package objects;

import java.util.UUID;

public class Address {
	private UUID ArrdressID;
	private String OrderShipName;
	private String OrderShipAddress1;
	private String OrderShipAddress2;
	private String OrderCity;
	private String OrderState;
	private String OrderCountry;
	private String OrderZip;

	public String getOrderShipName() {
		return OrderShipName;
	}

	public void setOrderShipName(String orderShipName) {
		OrderShipName = orderShipName;
	}

	public String getOrderShipAddress1() {
		return OrderShipAddress1;
	}

	public void setOrderShipAddress1(String orderShipAddress1) {
		OrderShipAddress1 = orderShipAddress1;
	}

	public String getOrderShipAddress2() {
		return OrderShipAddress2;
	}

	public void setOrderShipAddress2(String orderShipAddress2) {
		OrderShipAddress2 = orderShipAddress2;
	}

	public String getOrderCity() {
		return OrderCity;
	}

	public void setOrderCity(String orderCity) {
		OrderCity = orderCity;
	}

	public String getOrderState() {
		return OrderState;
	}

	public void setOrderState(String orderState) {
		OrderState = orderState;
	}

	public String getOrderCountry() {
		return OrderCountry;
	}

	public void setOrderCountry(String orderCountry) {
		OrderCountry = orderCountry;
	}

	public String getOrderZip() {
		return OrderZip;
	}

	public void setOrderZip(String orderZip) {
		OrderZip = orderZip;
	}

	public UUID getArrdressID() {
		return ArrdressID;
	}

	public void setArrdressID() {
		ArrdressID = UUID.randomUUID();
	}

	public void setArrdressID(UUID addressID) {
		// TODO Auto-generated method stub
		this.ArrdressID = addressID;
	}
}
