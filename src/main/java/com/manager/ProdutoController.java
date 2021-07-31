package com.manager;


import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.repositories.ProdutoRepository;
import com.resources.Produto;

@Path("supermercado")
public class ProdutoController {
	private ProdutoRepository produtoRepository;
	
	public ProdutoController() {
		this.produtoRepository = new ProdutoRepository();
	}
	
	@GET
	@Path("produto")
	@Produces(MediaType.APPLICATION_JSON)
	public Response HelloWord(@QueryParam("listaProdutos") String produtos) {
		List<String> nomesProdutos = Arrays.asList(produtos.split(","));
		return Response.ok(produtoRepository.consultarProdutos(nomesProdutos)).build();
	}
	
	@POST
	@Path("produto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Save(Produto produto) {
		produtoRepository.cadastrarProduto(produto);
		return Response.ok(produto).build();
	}
	
	@POST
	@Path("produtos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response SaveAll(List<Produto> produtos) {
		produtoRepository.cadastrarProdutos(produtos);
		return Response.ok(produtos).build();
	}
	
	@DELETE
	@Path("produto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Produto produto) {
		produtoRepository.removerProduto(produto);
		return Response.ok(produto).build();
	}
}
