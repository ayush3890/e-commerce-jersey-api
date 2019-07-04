package com.ayush.demo_rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class about {
	private String name;
	private int phno;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhno() {
		return phno;
	}

	public void setPhno(int phno) {
		this.phno = phno;
	}
}
