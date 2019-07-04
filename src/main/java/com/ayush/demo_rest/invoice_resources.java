package com.ayush.demo_rest;

import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import objects.Invoice;
import objects.Invoice_Item;
import objects.Item;

@Path("invoice")
@XmlRootElement
public class invoice_resources {
	GetFromDB db = new GetFromDB();

	@POST
	@Path("create")
	public Response invoice_create(@HeaderParam("Authorization") String auth_token,
			@FormParam("BuyerID") String buyerID, @FormParam("InvoiceItemID") String invoiceItemID,
			@FormParam("OrderID") String orderID, @FormParam("SellerID") String sellerID,
			@FormParam("Details") String details) throws JSONException {

		Invoice new_invoice = new Invoice();
		new_invoice.setInvoiceID();
//		new_invoice.setInvoiceItemID(UUID.fromString(invoiceItemID));
		new_invoice.setOrderID(UUID.fromString(orderID));
		new_invoice.setSellerID(UUID.fromString(sellerID));
		new_invoice.setBuyerID(UUID.fromString(buyerID));

		ArrayList<Invoice_Item> InvoiceOrderItems = new ArrayList<Invoice_Item>();
		JSONArray jsonarray = new JSONArray(details);
		for (int i = 0; i < jsonarray.length(); i++) {
			Invoice_Item newItem = new Invoice_Item();
			JSONObject itemJSONObject = jsonarray.getJSONObject(i);

			newItem.setProductID(UUID.fromString(itemJSONObject.getString("ProductID")));
			newItem.setQtyRequired(Integer.parseInt(itemJSONObject.getString("QtyRequired")));
			newItem.setQtyDispatched(Integer.parseInt(itemJSONObject.getString("QtyDispatched")));

			InvoiceOrderItems.add(newItem);
		}
		new_invoice.setInvoiceItems(InvoiceOrderItems);
		db.createInvoice(new_invoice);
//		System.out.println(new_invoice);
		JSONObject response = new JSONObject();
		try {
			response.append("response code", 200);
			response.append("response status", "order created");
			response.append("new_order", new_invoice);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(response.toString()).build();
	}
}
