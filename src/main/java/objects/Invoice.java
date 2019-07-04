package objects;

import java.util.ArrayList;
import java.util.UUID;

public class Invoice {
//	private UUID InvoiceItemID;
	private UUID InvoiceID;
	private UUID SellerID;
	private UUID BuyerID;
	private UUID OrderID;
	private ArrayList<Invoice_Item> InvoiceItems;

//	public UUID getInvoiceItemID() {
//		return InvoiceItemID;
//	}
//
//	public void setInvoiceItemID(UUID invoiceItemID) {
//		InvoiceItemID = invoiceItemID;
//	}

	public UUID getInvoiceID() {
		return InvoiceID;
	}

	public void setInvoiceID() {
		InvoiceID = UUID.randomUUID();
	}

	public UUID getSellerID() {
		return SellerID;
	}

	public void setSellerID(UUID sellerID) {
		SellerID = sellerID;
	}

	public UUID getOrderID() {
		return OrderID;
	}

	public void setOrderID(UUID orderID) {
		OrderID = orderID;
	}

	public ArrayList<Invoice_Item> getInvoiceItems() {
		return InvoiceItems;
	}

	public void setInvoiceItems(ArrayList<Invoice_Item> invoiceOrderItems) {
		InvoiceItems = invoiceOrderItems;
	}

	public UUID getBuyerID() {
		return BuyerID;
	}

	public void setBuyerID(UUID buyerID) {
		BuyerID = buyerID;
	}

}
