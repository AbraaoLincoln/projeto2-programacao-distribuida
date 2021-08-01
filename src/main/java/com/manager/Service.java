package com.manager;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

@ApplicationPath("/api")
public class Service extends Application{
	private Set<Object> singletons = new HashSet<>();
	
	public Service() {
		this.singletons.add(new ProdutoController());
		this.singletons.add(new ViewController());
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}
