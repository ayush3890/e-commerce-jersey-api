
package com.ayush.demo_rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {

	/**
	 * Method processing HTTP GET requests, producing "text/plain" MIME media type.
	 * 
	 * @return String that will be send back as a response of type "text/plain".
	 */
	@GET
	@Produces("text/plain")
	public String getIt() {
		return "Hi there!";
	}

//	@GET
//	@Path("/login")
//	public Viewable loginPage() {
//		about model = new about();
//		model.setName("A ayush");
//		model.setPhno(12345);
//		about[] arr = new about[1];
//		arr[0] = model;
//		return new Viewable("/login", arr);
//	}
}
