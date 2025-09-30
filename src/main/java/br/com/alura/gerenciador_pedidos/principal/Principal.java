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

package br.com.alura.gerenciador_pedidos.principal;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRepository;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Principal {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }

    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void principal() {
        // Criando categorias
        Categoria categoriaEletronicos = new Categoria(1L, "Eletrônicos");
        Categoria categoriaLivros = new Categoria(2L, "Livros");

        // Criando produtos e associando às categorias
        Produto produto1 = new Produto("Notebook", 3500.0, categoriaEletronicos);
        Produto produto2 = new Produto("Smartphone", 2500.0, categoriaEletronicos);
        Produto produto3 = new Produto("Livro de Java", 100.0, categoriaLivros);
        Produto produto4 = new Produto("Livro de Spring Boot", 150.0, categoriaLivros);

        // Associando produtos às categorias
        //categoriaEletronicos.
        categoriaEletronicos.setProdutos(List.of(produto1, produto2));
        categoriaLivros.setProdutos(List.of(produto3, produto4));

        // Salvando categorias (cascateia produtos automaticamente, se configurado)
        categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        // Testando a persistência e o relacionamento
        System.out.println("Categorias e seus produtos:");
        categoriaRepository.findAll().forEach(categoria -> {
            System.out.println("Categoria: " + categoria.getNome());
            categoria.getProdutos().forEach(produto ->
                    System.out.println(" - Produto: " + produto.getNome())
            );
        });


//        // Testando consultas e verificando os relacionamentos
//        System.out.println("Produtos na categoria Eletrônicos:");
//        categoriaRepository.findById(1L).ifPresent(categoria ->
//                categoria.getProdutos().forEach(produto ->
//                        System.out.println(" - " + produto.getNome())
//                )
//        );



    }
}
