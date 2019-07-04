package objects;

import java.util.UUID;

public class Product {
	private UUID ProductID;
	private UUID CreaterSellerID;
	private String Name;
	private String BrandName;
	private int price;
	private int discount;

	public UUID getProductID() {
		return ProductID;
	}

	public void setProductID() {
		ProductID = UUID.randomUUID();
	}

	public UUID getCreaterSellerID() {
		return CreaterSellerID;
	}

	public void setCreaterSellerID(UUID createrSellerID) {
		CreaterSellerID = createrSellerID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		BrandName = brandName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
