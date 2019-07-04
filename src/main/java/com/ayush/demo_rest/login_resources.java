package com.ayush.demo_rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import objects.Seller;

@Path("login")
@XmlRootElement
public class login_resources {
	GetFromDB db = new GetFromDB();
	private String Authorization = "1234";

	@POST
	@Path("seller-signup")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

	public Response sellerSignup(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("phno") String phno, @FormParam("password") String password,
			@HeaderParam("Authorization") String auth_token) {

		if (!authorize(auth_token)) {
			return Response.status(200).entity("Access Denied").build();
		}

//		System.out.println(auth_token);
		Seller s1 = db.seller_with_email_or_phone(email, phno);
		if (s1 == null) {
			Seller new_seller = new Seller();
			new_seller.setSellerId();
			new_seller.setName(name);
			new_seller.setEmail(email);
			new_seller.setPassword(password);
			new_seller.setPhno(phno);
			db.create_seller(new_seller);
			JSONObject response = new JSONObject();
			try {
				response.append("response code", 200);
				response.append("response status", "seller created");
				response.append("new_seller", new_seller);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return Response.status(200).entity(response.toString()).build();
		} else {
			return Response.status(200).entity("User Already Exist").build();
		}

	}

	@POST
	@Path("seller-login")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response sellerLogin(@FormParam("email") String email, @FormParam("password") String password,
			@FormParam("phno") String phno, @HeaderParam("Authorization") String auth_token) {

		if (!authorize(auth_token)) {
			return Response.status(200).entity("Access Denied").build();
		}
		
		Seller seller = db.seller_with_email_or_phone(email, phno);
		
		if (seller == null) {
			return Response.status(200).entity("User Does Not Exist").build();
		} else {
			JSONObject response = new JSONObject();
			try {
				if (verify_user(seller, password)) {
					response.append("response code", 200);
					response.append("response status", "Seller Logged In");
					response.append("new_seller", seller);
				} else {
					response.append("response code", 200);
					response.append("response status", "Wrong Password");
//					response.append("new_seller", seller);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return Response.status(200).entity(response.toString()).build();
		}
	}

	private boolean verify_user(Seller seller, String password) {
		return seller.getPassword().contentEquals(password);
	}

	private boolean authorize(String auth_token) {
		return auth_token.equals(Authorization);
	}

}
