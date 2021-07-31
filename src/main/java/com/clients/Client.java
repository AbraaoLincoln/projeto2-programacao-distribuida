package com.clients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.resources.Produto;

import exceptions.ResponseException;

public class Client {
	public static void main(String[] args) {
		final String path = "http://localhost:8080/projeto2_programacao_distribuida_rest/api";
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
		ConsultaProdutos server = target.proxy(ConsultaProdutos.class);
		
		Client.showMenuConsulta(server);
		 

	}
	
	
	public static void showMenuConsulta(ConsultaProdutos server) {
		 Scanner sc = new Scanner(System.in);
         String input = "0";

         System.out.println("==============================");
         System.out.println(" =   Consulta de Produtos   = ");
         System.out.println("==============================");
         System.out.println("");

         try {
        	 do {
                 List<String> produtos = new ArrayList<>();
                 System.out.println("Digite a lista de produtos:");
                 input = sc.nextLine();

                 for(String produto : input.split(",")) {
                     produtos.add(produto);
                 }
                 
                 Response res = server.produtos(input);
                 if(res.getStatus() != 200) throw new ResponseException();
                 Map<String, ArrayList<Produto>> resultadoConsulta =  res.readEntity(new GenericType<HashMap<String, ArrayList<Produto>>>() { });
                 
                 System.out.println("");
                 Client.showResultado(produtos, resultadoConsulta);

                 System.out.println("");
                 System.out.println("Deseja fazer outra consulta ?");
                 System.out.println("0 - nao | 1 - sim");
                 input = sc.nextLine();
             }while (!input.equals("0"));

             System.out.println("Programa finalizado");
		} catch (ResponseException re) {
			System.out.println("Algo de errado.");
		}
	}
	
	public static void showResultado(List<String> produtos, Map<String, ArrayList<Produto>> resultado) {
        System.out.println("===============================");
        System.out.println(" =   Resultado da Consulta   = ");
        System.out.println("===============================");
        for(String nomeProduto : produtos) {
            if(resultado.containsKey(nomeProduto)) {
                List<Produto> listaProdutos = resultado.get(nomeProduto);
                String supermercados = "";

                for(Produto p : listaProdutos) {
                    supermercados += p.getSupermercado() + ", ";
                }

                System.out.println("Produto: " + nomeProduto);
                System.out.println("Melhor preco: " + listaProdutos.get(0).getPreco() + " no " + listaProdutos.get(0).getSupermercado());
                System.out.println("Supermercados consultados: " + supermercados);
            }else {
                System.out.println("Nada foi encontado para o produto " + nomeProduto);
            }

            System.out.println("-------------------------------");
        }
    }
}
