//package br.com.alura.gerenciador_pedidos.principal;
//
//import br.com.alura.gerenciador_pedidos.model.Categoria;
//import br.com.alura.gerenciador_pedidos.model.Pedido;
//import br.com.alura.gerenciador_pedidos.model.Produto;
//import br.com.alura.gerenciador_pedidos.repository.CategoriaRepository;
//import br.com.alura.gerenciador_pedidos.repository.PedidoRepository;
//import br.com.alura.gerenciador_pedidos.repository.ProdutosRepository;
//
//import java.time.LocalDate;
//
//public class Principal {
//    private ProdutosRepository produtosRepository;
//    private PedidoRepository pedidoRepository;
//    private CategoriaRepository categoriaRepository;
//
//    public Principal(PedidoRepository pedidoRepository, ProdutosRepository produtosRepository, CategoriaRepository categoriaRepository) {
//        this.pedidoRepository = pedidoRepository;
//        this.produtosRepository = produtosRepository;
//        this.categoriaRepository = categoriaRepository;
//    }
//
//    public void salvaTabelas() {
//        System.out.println("Executado a propriedade mostra");
//
//        Produto produto = new Produto("Produto 2", 80.0);
//
//        Categoria categoria = new Categoria(2L,"Categoria 2");
//
//        Pedido pedido = new Pedido(2L, LocalDate.now());
//
//        produtosRepository.save(produto);
//        categoriaRepository.save(categoria);
//        pedidoRepository.save(pedido);
//
//        System.out.println(produto);
//        System.out.println(categoria);
//        System.out.println(pedido);
//    }
//}
//

//Um exemplo da mesma falha do categoriaRepository null
//https://cursos.alura.com.br/forum/topico-duvida-excecao-nullpointerexception-categoryrepository-saveall-java-lang-iterable-515255

package br.com.alura.gerenciador_pedidos.principal;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Fornecedor;
import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRepository;
import br.com.alura.gerenciador_pedidos.repository.FornecedorRepository;
import br.com.alura.gerenciador_pedidos.repository.PedidoRepository;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Principal {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    //@Autowired
    public Principal() {
    }

    public FornecedorRepository getFornecedorRepository() {
        return fornecedorRepository;
    }
    public void setFornecedorRepository(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public ProdutoRepository getProdutoRepository() {
        return produtoRepository;
    }
    public void setProdutoRepository(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public PedidoRepository getPedidoRepository() {
        return pedidoRepository;
    }
    public void setPedidoRepository(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public void principal() {
        // Criando categorias
        Categoria categoriaEletronicos = new Categoria("Eletrônicos");
        Categoria categoriaLivros = new Categoria("Livros");
        categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        // Criando fornecedor
        Fornecedor fornecedorTech = new Fornecedor("Tech Supplier");
        Fornecedor fornecedorLivros = new Fornecedor("Livraria Global");
        fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));

        // Criando produtos e associando às categorias
        Produto produto1 = new Produto("Notebook", 3500.0, categoriaEletronicos, fornecedorTech);
 //        Produto produto2 = new Produto("Smartphone", 2500.0, categoriaEletronicos, fornecedorTech);
        Produto produto2 = new Produto("Livro de Java", 100.0, categoriaLivros, fornecedorLivros);
//        //Produto produto4 = new Produto("Livro de Spring Boot", 150.0, categoriaLivros, fornecedorLivros);
//        produto1.setFornecedor(fornecedorTech);
//        produto2.setFornecedor(fornecedorTech);
//        produto3.setFornecedor(fornecedorLivros);
//        produto1.setCategoria(categoriaEletronicos);
//        produto2.setCategoria(categoriaEletronicos);
//        produto3.setCategoria(categoriaLivros);
//        produtoRepository.saveAll(List.of(produto1, produto2, produto3));
        produtoRepository.saveAll(List.of(produto1, produto2));
//
//        //Criando pedidos e associando produtos
        Pedido pedido1 = new Pedido(1L, LocalDate.now());
        pedido1.setProdutos(List.of(produto1, produto2));

        Pedido pedido2 = new Pedido(2L, LocalDate.now().minusDays(1));
        pedido2.setProdutos(List.of(produto2));
        pedidoRepository.saveAll(List.of(pedido1, pedido2));

        // Testando consultas e verificando os relacionamentos
        System.out.println("\n********---> Produtos na categoria Eletrônicos:");
        categoriaRepository.findById(1L).ifPresent(categoria ->
                categoria.getProdutos().forEach(produto ->
                        System.out.println(" - " + produto.getNome())
                )
        );

        System.out.println("\n********---> Pedidos e seus produtos:");
        pedidoRepository.findAll().forEach(pedido -> {
            System.out.println("Pedido " + pedido.getId() + ":");
            pedido.getProdutos().forEach(produto ->
                    System.out.println(" - " + produto.getNome())
            );
        });

        System.out.println("\n********---> Produtos e seus fornecedores:");
        produtoRepository.findAll().forEach(produto ->
                System.out.println("Produto: " + produto.getNome() +
                        ", Fornecedor: " + produto.getFornecedor().getNome())
        );

        System.out.println("\n");

    }
}
