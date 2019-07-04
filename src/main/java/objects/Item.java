package objects;

import java.util.ArrayList;
import java.util.UUID;

public class Item {
	private UUID OrderID;
	private UUID ProductID;
	private int QtyRequired;
	private int QtyDispatched;
	private ArrayList<UUID> InvoiceIds;

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

	public void setQtyDispatched() {
		QtyDispatched = 0;
	}

	public ArrayList<UUID> getInvoiceIds() {
		return InvoiceIds;
	}

	public void setInvoiceIds() {
		InvoiceIds = new ArrayList<UUID>();
	}

	public UUID getOrderID() {
		return OrderID;
	}

	public void setOrderID(UUID orderID) {
		OrderID = orderID;
	}

}
