package com.ayush.demo_rest;

import java.util.UUID;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;

import objects.Product;

@Path("product")
@XmlRootElement
public class product_resources {
	GetFromDB db = new GetFromDB();

	@POST
	@Path("create")
	public Response invoice_create(@HeaderParam("Authorization") String auth_token,
			@FormParam("SellerID") String createrSellerID, @FormParam("Name") String name,
			@FormParam("Brand") String brand, @FormParam("Price") int price, @FormParam("Discount") int discount)
			throws JSONException {

		Product new_product = new Product();
		new_product.setProductID();
		new_product.setCreaterSellerID(UUID.fromString(createrSellerID));
		new_product.setName(name);
		new_product.setBrandName(brand);
		new_product.setPrice(price);
		new_product.setDiscount(discount);
		db.crete_product(new_product);
		return null;
	}
}
