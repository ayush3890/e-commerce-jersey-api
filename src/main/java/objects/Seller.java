package objects;

import java.util.UUID;

public class Seller {
	private UUID SellerId;
	private String name;
	private String phno;
	private String email;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		String res = "";
		res += this.name;
		return res;
	}

	public UUID getSellerId() {
		return SellerId;
	}

	public void setSellerId() {
		SellerId = UUID.randomUUID();
	}
}
