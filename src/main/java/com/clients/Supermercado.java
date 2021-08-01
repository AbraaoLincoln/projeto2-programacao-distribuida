package com.clients;

import java.util.Scanner;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.resources.Produto;

import exceptions.ResponseException;

public class Supermercado {

	public static void main(String[] args) {
		final String path = "http://localhost:8080/projeto2_programacao_distribuida_rest/api";
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
		CadastrarProdutos server = target.proxy(CadastrarProdutos.class);
		
		Supermercado.showMenuCadastroProduto(server);
		
	}
	
	public static void showMenuCadastroProduto(CadastrarProdutos server) {
		 try{
            Scanner sc = new Scanner(System.in);
            String input = "0";
            float preco = 0;
            System.out.println("==============================");
            System.out.println(" =   Cadastro de Produtos   =");
            System.out.println("==============================");
            System.out.println("");
            do {
                Produto produtoParaCadastrar = new Produto("", "", 0);

                System.out.println("Digite o nome do supermercado:");
                input = sc.nextLine();
                produtoParaCadastrar.setSupermercado(input);

                System.out.println("Digite o nome do produto:");
                input = sc.nextLine();
                produtoParaCadastrar.setNome(input);

                System.out.println("Digite o preco do produto:");
                preco = sc.nextFloat();
                produtoParaCadastrar.setPreco(preco);

                sc.nextLine();
                
                Response res = server.cadastrarProduto(produtoParaCadastrar);
                if(res.getStatus() != 200) throw new ResponseException();
                System.out.println(">> Produto cadastrado com sucesso! <<");

                System.out.println("");
                System.out.println("Deseja adiconar mais produtos ?");
                System.out.println("0 - nao | 1 - sim");
                input = sc.nextLine();
            }while (!input.equals("0"));
            
            sc.close();
            System.out.println("Programa finalizado");
        }catch (ResponseException re) {
            System.out.println(">> Error ao cadastrar o produto! <<");
        }
	}

}
