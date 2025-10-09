
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

import java.time.LocalDate;
import java.util.List;

//@Component
public class Principal {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

<<<<<<< HEAD
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

=======
>>>>>>> 471832b5b0a54825ec7f94b5575f998a840f55d1
    //@Autowired
    public Principal() {
    }

<<<<<<< HEAD
    public FornecedorRepository getFornecedorRepository() {
        return fornecedorRepository;
    }
    public void setFornecedorRepository(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

=======
>>>>>>> 471832b5b0a54825ec7f94b5575f998a840f55d1
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

<<<<<<< HEAD
    public PedidoRepository getPedidoRepository() {
        return pedidoRepository;
    }
    public void setPedidoRepository(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

=======
>>>>>>> 471832b5b0a54825ec7f94b5575f998a840f55d1
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

        // Associando produtos às categorias
        //categoriaEletronicos.
//        categoriaEletronicos.setProdutos(List.of(produto1, produto2));
//        categoriaLivros.setProdutos(List.of(produto3, produto4));

        // Salvando categorias (cascateia produtos automaticamente, se configurado)
//        categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        // Testando a persistência e o relacionamento
<<<<<<< HEAD
//        System.out.println("Categorias e seus produtos:");
//        categoriaRepository.findAll().forEach(categoria -> {
//            System.out.println("Categoria: " + categoria.getNome());
//            categoria.getProdutos().forEach(produto ->
//                    System.out.println(" - Produto: " + produto.getNome())
//            );
//        });

//        // Testando consultas e verificando os relacionamentos
//        System.out.println("Produtos na categoria Eletrônicos:");
//        categoriaRepository.findById(1L).ifPresent(categoria ->
//                categoria.getProdutos().forEach(produto ->
//                        System.out.println(" - " + produto.getNome())
//                )
//        );
=======
        System.out.println("Categorias e seus produtos:");
        categoriaRepository.findAll().forEach(categoria -> {
            System.out.println("Categoria: " + categoria.getNome());
            categoria.getProdutos().forEach(produto ->
                    System.out.println(" - Produto: " + produto.getNome())
            );
        });

//        // Testando consultas e verificando os relacionamentos
        System.out.println("Produtos na categoria Eletrônicos:");
        categoriaRepository.findById(1L).ifPresent(categoria ->
                categoria.getProdutos().forEach(produto ->
                        System.out.println(" - " + produto.getNome())
                )
        );
>>>>>>> 471832b5b0a54825ec7f94b5575f998a840f55d1
    }
}
