package objects;

import java.util.UUID;

public class Invoice_Item {
	private UUID InvoiceID;
	private UUID ProductID;
	private int QtyRequired;
	private int QtyDispatched;
	private UUID AddressID;
	
	public UUID getInvoiceID() {
		return InvoiceID;
	}

	public void setInvoiceID(UUID invoiceID) {
		InvoiceID = invoiceID;
	}

	public UUID getProductID() {
		return ProductID;
	}

	public void setProductID(UUID productID) {
		ProductID = productID;
	}

	public int getQtyRequired() {
		return QtyRequired;
	}

	public void setQtyRequired(int qtyRequired) {
		QtyRequired = qtyRequired;
	}

	public int getQtyDispatched() {
		return QtyDispatched;
	}

	public void setQtyDispatched(int qtyDispatched) {
		QtyDispatched = qtyDispatched;
	}

	public UUID getAddressID() {
		return AddressID;
	}

	public void setAddressID(UUID addressID) {
		AddressID = addressID;
	}
}
