package com.manager;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class Service extends Application{
	private Set<Object> singletons = new HashSet<>();
	
	public Service() {
		this.singletons.add(new ProdutoController());
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
