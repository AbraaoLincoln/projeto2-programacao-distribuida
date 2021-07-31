package com.clients;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.resources.Produto;

@Path("supermercado")
public interface ConsultaProdutos {
	@GET
	@Path("produto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response produtos(@QueryParam("listaProdutos") String produtos);
	
}
