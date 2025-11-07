package br.com.alura.gerenciador_pedidos.principal;

import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRepository;
import br.com.alura.gerenciador_pedidos.repository.FornecedorRepository;
import br.com.alura.gerenciador_pedidos.repository.PedidoRepository;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
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
                    10 - Consulta para retornar os produtos/categoria filtrados por nome contido no produto e/ou categoria
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
                case 6:
                    mediaDePrecoPorProduto();
                    break;
                case 7:
                    precoMmaximoDeUmProdutoEmUmaCategoria();
                    break;
                case 8:
                    quantidadeDeProdutosPorCategoria();
                    break;
                case 9:
                    categoriasComMaisDeXProdutos();
                    break;
                case 10:
                    produtosFiltradosPorNomeOuPorCategoria();
                    break;
                case 11:
                    cincoProdutosMaisCaros();
                    break;
                default:
                    System.out.println("Opção inválida...");
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
        List<Produto> produtos = produtoRepository.produtosQueIniciamComUmaLetraEspecífica(letra);
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

    //6  - Consulta que retorna a média de preços dos produtos
    private void mediaDePrecoPorProduto() {
        System.out.println("\nLista da Média de preços por produto:");
        var mediaDePreco = produtoRepository.mediaDePrecoPorProduto();
        System.out.println(mediaDePreco);
        pausaTela();
    }

    //7  - Consulta que retorna o preço máximo de um produto em uma categoria
    private void precoMmaximoDeUmProdutoEmUmaCategoria() {
        System.out.println("\nInforme a categoria:");
        var nomeCategoria = leitura.nextLine();
        var precoMaximo = produtoRepository.precoMmaximoDeUmProdutoEmUmaCategoria(nomeCategoria);
        System.out.println("\nPreço máximo em produtos da categoria: " + nomeCategoria + " é: " + precoMaximo);
        pausaTela();
    }

    //8  - Consulta para contar o número de produtos por categoria
    private void quantidadeDeProdutosPorCategoria() {

        List<Object[]> produtos = produtoRepository.quantidadeDeProdutosPorCategoria();

        int qtdLista = produtos.size();
        for (int i = 0; i < qtdLista; i++) {
            System.out.println("Qtd. de produto da categoria: " + Arrays.toString(produtos.get(i)));
        }
        pausaTela();
        //produtos.forEach(System.out::println);
        //pausaTela();
    }

    //9  - Consulta para filtrar categorias com mais de 10 produtos
    private void categoriasComMaisDeXProdutos() {
        System.out.println("Informe a quantidade de produtos por categoria:");
        int qtdProdutos = leitura.nextInt();
        List<Object[]> produtos = produtoRepository.categoriasComMaisDeXProdutos(qtdProdutos);

        int qtdLista = produtos.size();
        for (int i = 0; i < qtdLista; i++) {
            System.out.println("Categoria com mais de " + qtdProdutos + " produtos: " + Arrays.toString(produtos.get(i)));
        }
        pausaTela();
    }

    //10 - Consulta para retornar os produtos filtrados por nome ou por categoria
    private void produtosFiltradosPorNomeOuPorCategoria() {
        System.out.println("\nInforme o nome do produto ou categoria");
        var nome = leitura.nextLine();

        List<Object[]> produtos = produtoRepository.produtosFiltradosPorNomeOuPorCategoria(nome.toLowerCase());

        int qtdLista = produtos.size();
        for (int i = 0; i < qtdLista; i++) {
            System.out.println("Produtos e/ou Categorias filtradas no nome " + nome + " : " + Arrays.toString(produtos.get(i)));
        }
        pausaTela();
    }

    //11 - Consulta nativa para buscar os cinco produtos mais caros
    private void cincoProdutosMaisCaros() {
        List<Produto> produtos = produtoRepository.cincoProdutosMaisCaros();
        System.out.println("\nLista dos 5 produtos mais caros.");
        produtos.forEach(System.out::println);
        pausaTela();
    }
}

//List<Object[]> resultados = new ArrayList<>();
