package com.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.resources.Produto;


public class ProdutoRepository {
	private HashMap<String, TreeMap<Float, ArrayList<Produto>>> produtos;

    public ProdutoRepository() {
        produtos = new HashMap<>();
    }

    public void cadastrarProduto(Produto produto) {
        if(produtos.containsKey(produto.getNome())) {
            TreeMap<Float, ArrayList<Produto>> produtosPorPreco = produtos.get(produto.getNome());
            if(!produtosPorPreco.containsKey(produto.getPreco())) {
                produtosPorPreco.put(produto.getPreco(), new ArrayList<>());
            }
            produtosPorPreco.get(produto.getPreco()).add(produto);
        }else {
            TreeMap<Float, ArrayList<Produto>> produtosPorPreco = new TreeMap<>();
            produtosPorPreco.put(produto.getPreco(), new ArrayList<>());
            produtosPorPreco.get(produto.getPreco()).add(produto);
            produtos.put(produto.getNome(), produtosPorPreco);
        }

       // System.out.println(produtos);
    }

    public void cadastrarProdutos(List<Produto> produtos) {
    	for(Produto produto : produtos) {
    		cadastrarProduto(produto);
    	}
    }

    public void removerProduto(Produto produto) {
        if(produtos.containsKey(produto.getNome())) {

            if(!produtos.get(produto.getNome()).get(produto.getPreco()).isEmpty()) {
                produtos.get(produto.getNome()).get(produto.getPreco()).remove(produto);
            }

            if(produtos.get(produto.getNome()).get(produto.getPreco()).isEmpty()) {
                produtos.get(produto.getNome()).remove(produto.getPreco());
            }

            if(produtos.get(produto.getNome()).isEmpty()) {
                produtos.remove(produto.getNome());
            }
        }
        //System.out.println(produtos);
    }

    public void removerProdutos(List<Produto> produtos) {
    	for(Produto produto : produtos) {
    		removerProduto(produto);
    	}
    }

    public Map<String, ArrayList<Produto>>  consultarProdutos(List<String> nomesProdutos) {
        HashMap<String, ArrayList<Produto>> produtosEncontrados = new HashMap<>();

        for(String nomeProduto : nomesProdutos) {
            if(produtos.containsKey(nomeProduto)) {
                ArrayList<Produto> listaProdutosEncontrados = new ArrayList<>();
                for(Float preco : produtos.get(nomeProduto).keySet()) {
                    listaProdutosEncontrados.addAll(produtos.get(nomeProduto).get(preco));
                }
                produtosEncontrados.put(nomeProduto, listaProdutosEncontrados);
            }
        }

        return produtosEncontrados;
    }
}
