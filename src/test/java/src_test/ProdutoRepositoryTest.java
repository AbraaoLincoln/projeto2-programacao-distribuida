package meupacote;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class ProdutoRepositoryTest {
	private ProdutoRepository produtoRepository;
	private Produto produto1;
	private Produto produto2; 
	
	@Before
	public void setUp() {
		produtoRepository = new ProdutoRepository();
		produto1 = new Produto("precobom", "feijao", 18.0f);
		produto2 = new Produto("baratao", "feijao", 16.0f);
	}
	
	
	@Test
	public void testConsultarProdutos() {
		HashMap<String, TreeMap<Float, ArrayList<Produto>>> produtos = new HashMap<>();
		TreeMap<Float, ArrayList<Produto>> produtosPorPreco = new TreeMap<>();
        produtosPorPreco.put(produto1.getPreco(), new ArrayList<>());
        produtosPorPreco.get(produto1.getPreco()).add(produto1);
        produtos.put(produto1.getNome(), produtosPorPreco);
        produtoRepository = new ProdutoRepository(produtos);
        
        List<String> produtosParaConsultar = new ArrayList<>();
        produtosParaConsultar.add("feijao");
		Map<String, ArrayList<Produto>> resultado = produtoRepository.consultarProdutos(produtosParaConsultar);
		Produto pCadastrado = resultado.get("feijao").get(0);
		
		assertEquals(produto1.getSupermercado(),pCadastrado.getSupermercado());
		assertEquals(produto1.getNome(),pCadastrado.getNome());
		assertEquals(produto1.getPreco(), pCadastrado.getPreco(), 0.0);
	}
	

	@Test
	public void testCadastrarProdutor() {
		produtoRepository.cadastrarProduto(produto1);
		
		List<String> produtos = new ArrayList<>();
		produtos.add("feijao");
		Map<String, ArrayList<Produto>> resultado = produtoRepository.consultarProdutos(produtos);
		Produto pCadastrado = resultado.get("feijao").get(0);
		
		assertEquals(produto1.getSupermercado(),pCadastrado.getSupermercado());
		assertEquals(produto1.getNome(),pCadastrado.getNome());
		assertEquals(produto1.getPreco(), pCadastrado.getPreco(), 0.0);
		
	}

	@Test
	public void testCadastrarListaDeProdutos() {
		List<Produto> produtos = new ArrayList<>();
		produtos.add(produto1);
		produtos.add(produto2);
		produtoRepository.cadastrarProdutos(produtos);
		
		List<String> produtosParaConsultar = new ArrayList<>();
		produtosParaConsultar.add("feijao");
		Map<String, ArrayList<Produto>> resultado = produtoRepository.consultarProdutos(produtosParaConsultar);
		
		Produto p1Cadastrado = resultado.get("feijao").get(1);
		
		assertEquals(produto1.getSupermercado(),p1Cadastrado.getSupermercado());
		assertEquals(produto1.getNome(),p1Cadastrado.getNome());
		assertEquals(produto1.getPreco(), p1Cadastrado.getPreco(), 0.0);
		
		Produto p2Cadastrado = resultado.get("feijao").get(0);
		
		assertEquals(produto2.getSupermercado(),p2Cadastrado.getSupermercado());
		assertEquals(produto2.getNome(),p2Cadastrado.getNome());
		assertEquals(produto2.getPreco(), p2Cadastrado.getPreco(), 0.0);
		
	}
	
	@Test
	public void testRemoverProduto() {
		produtoRepository.cadastrarProduto(produto1);
		produtoRepository.removerProduto(produto1);
		
		List<String> produtosParaConsultar = new ArrayList<>();
		produtosParaConsultar.add("feijao");
		Map<String, ArrayList<Produto>> resultado = produtoRepository.consultarProdutos(produtosParaConsultar);
		
		assertNull(resultado.get("feijao"));
	}
	
	@Test
	public void testRemoverListaProduto() {
		List<Produto> produtos = new ArrayList<>();
		produtos.add(produto1);
		produtos.add(produto2);
		produtoRepository.removerProdutos(produtos);
		
		List<String> produtosParaConsultar = new ArrayList<>();
		produtosParaConsultar.add("feijao");
		Map<String, ArrayList<Produto>> resultado = produtoRepository.consultarProdutos(produtosParaConsultar);
		
		assertNull(resultado.get("feijao"));
	}
}
