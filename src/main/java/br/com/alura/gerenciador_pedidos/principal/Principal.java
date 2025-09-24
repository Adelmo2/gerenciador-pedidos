package br.com.alura.gerenciador_pedidos.principal;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRepository;
import br.com.alura.gerenciador_pedidos.repository.PedidoRepository;
import br.com.alura.gerenciador_pedidos.repository.ProdutosRepository;

import java.time.LocalDate;

public class Principal {
    private ProdutosRepository produtosRepository;
    private PedidoRepository pedidoRepository;
    private CategoriaRepository categoriaRepository;

    public Principal(PedidoRepository pedidoRepository, ProdutosRepository produtosRepository, CategoriaRepository categoriaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtosRepository = produtosRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public void salvaTabelas() {
        System.out.println("Executado a propriedade mostra");

        Produto produto = new Produto("Produto 2", 80.0);

        Categoria categoria = new Categoria(2L,"Categoria 2");

        Pedido pedido = new Pedido(2L, LocalDate.now());

        produtosRepository.save(produto);
        categoriaRepository.save(categoria);
        pedidoRepository.save(pedido);

        System.out.println(produto);
        System.out.println(categoria);
        System.out.println(pedido);
    }
}
