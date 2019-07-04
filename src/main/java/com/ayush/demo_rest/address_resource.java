package com.ayush.demo_rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import objects.Address;
import objects.Seller;

@Path("address")
@XmlRootElement
public class address_resource {
	GetFromDB db = new GetFromDB();
	private String Authorization = "1234";

	@POST
	@Path("create")
	public Response sellerSignup(@HeaderParam("Authorization") String auth_token,
			@FormParam("OrderShipName") String OrderShipName, @FormParam("OrderShipAddress1") String OrderShipAddress1,
			@FormParam("OrderShipAddress2") String OrderShipAddress2, @FormParam("OrderCity") String OrderCity,
			@FormParam("OrderState") String OrderState, @FormParam("OrderCountry") String OrderCountry,
			@FormParam("OrderZip") String OrderZip) {

		if (!authorize(auth_token)) {
			return Response.status(200).entity("Access Denied").build();
		}

//		System.out.println(auth_token);
		Address new_address = new Address();
		new_address.setArrdressID();
		new_address.setOrderShipName(OrderShipName);
		new_address.setOrderShipAddress1(OrderShipAddress1);
		new_address.setOrderShipAddress2(OrderShipAddress2);
		new_address.setOrderCity(OrderCity);
		new_address.setOrderState(OrderState);
		new_address.setOrderCountry(OrderCountry);
		new_address.setOrderZip(OrderZip);
		db.create_address(new_address);
		JSONObject response = new JSONObject();
		try {
			response.append("response code", 200);
			response.append("response status", "seller created");
			response.append("new_seller", new_address);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(response.toString()).build();

	}

	private boolean authorize(String auth_token) {
		return auth_token.equals(Authorization);
	}

}
