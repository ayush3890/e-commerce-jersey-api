package com.ayush.demo_rest;

import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import objects.Address;
import objects.Item;
import objects.Order;

@Path("order")
@XmlRootElement
public class order_resources {
	GetFromDB db = new GetFromDB();

	@POST
	@Path("create")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response order_create(@HeaderParam("Authorization") String auth_token,
			@FormParam("OrderUserId") String OrderUserId, @FormParam("OrderAmount") float OrderAmount,
			@FormParam("OrderSellerID") String OrderSellerID, @FormParam("OrderShipName") String OrderShipName,
			@FormParam("OrderShipAddress1") String OrderShipAddress1,
			@FormParam("OrderShipAddress2") String OrderShipAddress2, @FormParam("OrderCity") String OrderCity,
			@FormParam("OrderState") String OrderState, @FormParam("OrderCountry") String OrderCountry,
			@FormParam("OrderZip") String OrderZip, @FormParam("OrderEmail") String OrderEmail,
			@FormParam("OrderStatus") int OrderStatus, @FormParam("Details") String Details) throws JSONException {

		Order order = new Order();
		JSONArray jsonarray = new JSONArray(Details);
//		System.out.println(Details);
		ArrayList<Item> OrderItems = new ArrayList<Item>();

		for (int i = 0; i < jsonarray.length(); i++) {
			Item newItem = new Item();
			JSONObject itemJSONObject = jsonarray.getJSONObject(i);
			newItem.setProductID(UUID.fromString(itemJSONObject.getString("ProductID")));
			newItem.setQtyRequired(Integer.parseInt(itemJSONObject.getString("QtyRequired")));
			newItem.setQtyDispatched();
			newItem.setInvoiceIds();
			OrderItems.add(newItem);
		}

		order.setOrderId();
		order.setOrderUserId(UUID.fromString(OrderUserId));
		order.setOrtderItems(OrderItems);
		order.setOrderAmount(OrderAmount);
		order.setOrderSellerId(UUID.fromString(OrderSellerID));

		order.setOrderEmail(OrderEmail);
		order.setOrderDate();
		order.setOrderStatus(OrderStatus);
		order.setOrderTrackingNumber("OrderTrackingID");

		Address address = new Address();
		address.setOrderShipName(OrderShipName);
		address.setOrderShipAddress1(OrderShipAddress1);
		address.setOrderShipAddress2(OrderShipAddress2);
		address.setOrderCity(OrderCity);
		address.setOrderState(OrderState);
		address.setOrderCountry(OrderCountry);
		address.setOrderZip(OrderZip);

		db.createOrder(order);
		JSONObject response = new JSONObject();
		try {
			response.append("response code", 200);
			response.append("response status", "order created");
			response.append("new_order", order);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(response.toString()).build();
	}

	@POST
	@Path("accept")
	public Response accept_order(@FormParam("OrderID") String OrderID, @FormParam("SellerID") String SellerID) {
		String status = "Status";
		db.acceptOrder(UUID.fromString(OrderID), status);
		return null;
	}
	
	@POST
	@Path("reject")
	public Response reject_order(@FormParam("OrderID") String OrderID, @FormParam("SellerID") String SellerID) {
		String status = "Reject";
		db.acceptOrder(UUID.fromString(OrderID), status);
		return null;
	}
	
	@POST
	@Path("departed")
	public Response departed_order(@FormParam("OrderID") String OrderID, @FormParam("SellerID") String SellerID) {
		String status = "Departed";
		db.acceptOrder(UUID.fromString(OrderID), status);
		return null;
	}
	
	@POST
	@Path("received")
	public Response received_order(@FormParam("OrderID") String OrderID, @FormParam("SellerID") String SellerID) {
		String status = "Received";
		db.acceptOrder(UUID.fromString(OrderID), status);
		return null;
	}

}
