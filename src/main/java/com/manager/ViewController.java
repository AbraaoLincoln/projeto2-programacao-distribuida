package com.manager;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ViewController {
	@GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getHome() {
	   return getClass().getResourceAsStream("index.html");
    }
}
