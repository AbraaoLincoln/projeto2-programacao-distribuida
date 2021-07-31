package com.resources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Produto {
    private String supermercado;
    private String nome;
    private float preco;
    
    public Produto() {}
    
    public Produto(String s, String n, float p) {
        supermercado = s;
        nome = n;
        preco = p;
    }

    public String getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "(" + supermercado + ", " + nome + ", " + preco + ")";
    }
    
    @Override
    public boolean equals(Object o) {
    	
    	if(o instanceof Produto) {
    		Produto p = (Produto) o;
    		
    		return this.supermercado.equals(p.getSupermercado()) &&
    			   this.nome.equals(p.getNome()) && this.preco == p.getPreco();
    	}
    	
		return false;
    	
    }
}
