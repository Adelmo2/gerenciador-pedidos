package br.com.alura.gerenciador_pedidos.principal;

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
import java.util.Scanner;

@Component
public class Principal3 {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    private Scanner leitura = new Scanner(System.in);

    public Principal3() {};

    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoRepository getProdutoRepository() {
        return produtoRepository;
    }
    public void setProdutoRepository(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public FornecedorRepository getFornecedorRepository() {
        return fornecedorRepository;
    }
    public void setFornecedorRepository(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public void CadastrosEConsultas3() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    0  - Sair
                    1  - Consulta que retorna os produtos com preço maior que um valor
                    2  - Consulta que retorna os produtos ordenados pelo preço crescente
                    3  - Consulta que retorna os produtos ordenados pelo preço decrescente
                    4  - Consulta que retorna os produtos que comecem com uma letra específica
                    5  - Consulta que retorna os pedidos feitos entre duas datas
                    6  - Consulta que retorna a média de preços dos produtos
                    7  - Consulta que retorna o preço máximo de um produto em uma categoria
                    8  - Consulta para contar o número de produtos por categoria
                    9  - Consulta para filtrar categorias com mais de 10 produtos
                    10 - Consulta para retornar os produtos filtrados por nome ou por categoria
                    11 - Consulta nativa para buscar os cinco produtos mais caros
                    99 - Teste de Método
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    produtosComPrecoMaiorQueUmValor();
                    break;
                case 2:
                    produtosOrdenadosPeloPrecoCrescente();
                    break;
                case 3:
                    produtosOrdenadosPeloPrecoDecrescente();
                    break;
                case 4:
                    produtosQueComecemComUmaLetraEspecífica();
                    break;
                case 5:
                    pedidosCadastradosEntreDuasDatas();
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void pausaTela() {
        System.out.println("\nPressione qualquer tecla para continuar...");
        var sVar2 = leitura.nextLine();
        var sVar = leitura.nextLine();
    }

    //1  - Consulta que retorna os produtos com preço maior que um valor
    private void produtosComPrecoMaiorQueUmValor() {
        System.out.println("Informe o valor:");
        var valorProduto = leitura.nextDouble();
        List<Produto> produtos = produtoRepository.produtosComPrecoMaiorQueUmValor(valorProduto);
        System.out.println("\nProduto com o valor maior que " + valorProduto);
        produtos.forEach(System.out::println);
        pausaTela();
    }

    //2  - Consulta que retorna os produtos ordenados pelo preço crescente
    private void produtosOrdenadosPeloPrecoCrescente() {
        List<Produto> produtos = produtoRepository.produtosOrdenadosPeloPrecoCrescente();
        System.out.println("\nProdutos ordenados pelo preço crescente ");
        produtos.forEach(System.out::println);
        pausaTela();
    }

    //3  - Consulta que retorna os produtos ordenados pelo preço decrescente
    private void produtosOrdenadosPeloPrecoDecrescente() {
        List<Produto> produtos = produtoRepository.produtosOrdenadosPeloPrecoDecrescente();
        System.out.println("\nProdutos ordenados pelo preço decrescente ");
        produtos.forEach(System.out::println);
        pausaTela();
    }

    //4  - Consulta que retorna os produtos que comecem com uma letra específica
    private void produtosQueComecemComUmaLetraEspecífica() {
        System.out.println("Informa uma letra");
        var letra = leitura.nextLine();
        System.out.println("Produtos que iniciam o nome com a letra: " + letra);
        List<Produto> produtos = produtoRepository.produtosQueComecemComUmaLetraEspecífica(letra);
        produtos.forEach(System.out::println);
        pausaTela();
    }

    //5  - Consulta que retorna os pedidos feitos entre duas datas
    private void pedidosCadastradosEntreDuasDatas() {
        System.out.println("\nInforma a data inicio do pedido (formato yyyy-mm-dd):");
        var dataPedidoIni = leitura.nextLine();

        System.out.println("\nInforma a data final do pedido (formato yyyy-mm-dd):");
        var dataPedidoFim = leitura.nextLine();

        LocalDate dataIni = LocalDate.parse(dataPedidoIni);
        LocalDate dataFim = LocalDate.parse(dataPedidoFim);

        System.out.println("\nPedidos gerados na data de: "  + dataIni + " até: " + dataFim);
        List<Pedido> pedidos = pedidoRepository.pedidosCadastradosEntreDuasDatas(dataIni, dataFim);
        pedidos.forEach(p ->
                System.out.println("Pedido: " + p.getId() +  " data inclusão: " + p.getData())
        );
        pausaTela();

    }

}
