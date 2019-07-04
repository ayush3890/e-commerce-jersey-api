package com.ayush.demo_rest;

import java.util.ArrayList;

import javax.websocket.server.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

@Path("about")
@XmlRootElement
public class about_resource {
	GetFromDB db = new GetFromDB();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAbout() {
		return Response.ok(new Viewable("/login", db.getAll())).build();
	}

	@POST
//	@Path("about")
	public ArrayList<about> create(about a1) {
		return db.create(a1);
	}

	@PUT
	public ArrayList<about> update(about a1) {
		return db.update(a1);
	}

	@DELETE
	@Path("about/{PhNo}")
	public void delete(@PathParam("PhNo") int PhNo) {
		System.out.println(PhNo);
//		return null;
//		about a = db.getWithPhNo(PhNo);
//		System.out.println(a);
//		return db.delete(a);
//		if (a.getPhno() != -1) {
//		} else {
//			return null;
//		}
	}
}
