package br.com.alura.gerenciador_pedidos.principal;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Fornecedor;
import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRepository;
import br.com.alura.gerenciador_pedidos.repository.FornecedorRepository;
import br.com.alura.gerenciador_pedidos.repository.PedidoRepository;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRepository;
import br.com.alura.gerenciador_pedidos.util.DataUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

import static br.com.alura.gerenciador_pedidos.util.DataUtil.*;
import static org.yaml.snakeyaml.tokens.Token.ID.Value;

@Component
public class Principal2 {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    private Scanner leitura = new Scanner(System.in);

    public Principal2() {};

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
    public void CadastrosEConsultas() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
            0 - Sair
            1 - Cadastrar Fornecedor
            2 - Cadastrar Catagoria
            3 - Cadastrar Produto
            4 - Consultar ID do Fornecedor
            5 - Cadastrar Pedido
            6 - Consultar Pedidos
            ---------------------
            11 - Consultar produto por nome (exato)
            12 - Consultar produtos associados a uma categoria específica
            13 - Consulta produtos com preço maior que o valor fornecido.
            14 - Consulta produtos com preço menor que o valor fornecido.
            15 - Consulta produtos cujo nome contenha o termo especificado.
            16 - Consulta pedidos que ainda não possuem uma data de entrega.
            17 - Consulta pedidos com data de entrega preenchida.
            18 - Consulta produtos de uma categoria ordenados pelo preço de forma crescente.
            19 - Consulta produtos de uma categoria ordenados pelo preço de forma decrescente.
            20 - Consulta a contagem de produtos em uma categoria específica.
            21 - Consulta a contagem de produtos cujo preço seja maior que o valor fornecido.
            21 - Consulta produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado
            22 - Retorne produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado.
            23 - Retorne pedidos feitos após uma data específica.
            24 - Retorne pedidos feitos antes de uma data específica.
            25 - Retorne pedidos feitos em um intervalo de datas.
            26 - Retorne os três produtos mais caros.
            27 - Retorne os cinco produtos mais baratos de uma categoria.
            
            99 - Teste de Método
            """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            
            switch (opcao) {
                case 1:
                    cadastrarFornecedor();
                    break;
                case 2:
                    cadastrarCategoria();
                    break;
                case 3:
                    cadastrarProduto();
                    //opcao = 0;
                    break;
                case 4:
                    consultarIdfornecedor();
                    break;
                case 5:
                    cadastrarPedido();
                    break;
                case 6:
                    consultarPedidos();
                    break;
                case 11:
                    consultaPprodutosPeloNome();
                    pausaTela();
                    break;
                case 12:
                    consultaPprodutosPelCategoria();
                    pausaTela();
                    break;
                case 13:
                    produtosComPrecoMaiorQueValorFornecido();
                    pausaTela();
                    break;
                case 14:
                    produtosComPrecoMnorQueValorFornecido();
                    break;
                case 15:
                    produtosComNomeContido();
                    break;
                case 16:
                    consultaPedidosSemDataDeEntrega();
                    break;
                case 17:
                    consultaPedidosComDataDeEntrega();
                    break;
                case 18:
                    consultaProdutosDeUmaCategoriaOrdenadaPeloPreco();
                    break;
                case 19:
                    consultaProdutosDeUmaCategoriaOrdenadaPeloPrecoDecrescente();
                    break;
                case 20:
                    consultaContagemProdutosEemUmaCategoria();
                    break;
                case 21:
                    consultaContagemProdutosPprecoMaiorQueValorFornecido();
                    break;
                case 22:
                    produtosComPrecoMenorQueValorOuNomeContido();
                    break;
                case 23:
                    pedidosAposDataEspecífica();
                    break;
                case 24:
                    pedidosAntesDaDataEspecífica();
                    break;
                case 25:
                    pedidosEntre2Datas();
                    break;
                case 26:
                    listaDosTresProdutosNaisCaros();
                    break;
                case 27:
                    listaDosCincoProdutosNaisBaratos();
                case 99:
                    testeMetodo();
                    break;
                case 0:
                    System.out.println("Saindo...");
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

    private void cadastrarFornecedor() {
        System.out.println("Informa o nome do fornecedor:");
        var nomefornecedor = leitura.nextLine();

        Optional<Fornecedor> fornecedorConsultado = fornecedorRepository.findByNomeContainingIgnoreCase(nomefornecedor);
        if (fornecedorConsultado.isPresent()) {
            var nomeFornecedor = fornecedorConsultado.get().getNome();
            var idFornecedor = fornecedorConsultado.get().getId();
            System.out.println("\nFornecedor: " + nomeFornecedor + " - " + idFornecedor + " já cadastrado!");
            System.out.println("\n");
            return;
        }

        Fornecedor fornecedor = new Fornecedor(nomefornecedor);
        fornecedorRepository.saveAll(List.of(fornecedor));

        System.out.println("\n********---> Fornecedores Cadastrados:");
        fornecedorRepository.findAll().forEach(f -> {
            System.out.println("Fornecedor: " + f.getNome());
        });
    }

    private void cadastrarCategoria() {
        System.out.println("Informa o nome da categoria:");
        var nomecategoria = leitura.nextLine();
        Categoria categoria = new Categoria(nomecategoria);

        Optional<Categoria> categoriaConsultada = categoriaRepository.findByNomeContainingIgnoreCase(nomecategoria);
        if (categoriaConsultada.isPresent()) {
            var nomeCategoria = categoriaConsultada.get().getNome();
            var idCategoria = categoriaConsultada.get().getId();
            System.out.println("\nCategoria: " + nomeCategoria + " - " + idCategoria + " já cadastrada!");
            System.out.println("\n");
            return;
        }

        categoriaRepository.saveAll(List.of(categoria));

        System.out.println("\n******---> Catergorias Cadastradas:");
        categoriaRepository.findAll().forEach(c -> {
            System.out.println("Categoria: " + c.getNome());
        });
    }

    private void consultarIdfornecedor() {
        Boolean saida = false;
        while (!saida) {
            System.out.println("Informa o nome do fornecedor:");
            var nomefornecedor = leitura.nextLine();

            Optional<Fornecedor> fornecedorConsultado = fornecedorRepository.findByNomeContainingIgnoreCase(nomefornecedor);
            if (fornecedorConsultado.isPresent()) {
                var nomeFornecedor = fornecedorConsultado.get().getNome();
                var idFornecedor = fornecedorConsultado.get().getId();
                System.out.println("Fornecedor: " + nomeFornecedor + " ID: " + idFornecedor);
                saida = true;
            } else {
                System.out.println("Fornecedor não encontrado!");
            }
        }
    }

    private void cadastrarProduto() {
        System.out.println("Informa o nome do produto:");
        var nomeProduto = leitura.nextLine();

        System.out.println("Informa o valor do produto:");
        var valorProduto = leitura.nextLine();

        System.out.println("\n");
        fornecedorRepository.findAll().forEach(f -> {
            System.out.println("Fornecedor: " + f.getNome() + " ID: " + f.getId());
        });

        System.out.println("Informa o nome do fornecedor:");
        var nomeFornecedor2 = leitura.nextLine();
        Optional<Fornecedor> fornecedorConsultado2 = fornecedorRepository.findByNomeContainingIgnoreCase(nomeFornecedor2);
        Long idFornecedor = null;
        if (fornecedorConsultado2.isPresent()) {
            idFornecedor = fornecedorConsultado2.get().getId();
        } else {
            System.out.println("\n*** Fornecedor não encontrado! Pressione qualquer tecla para sair...");
            var sair = leitura.nextLine();
            System.exit(0);
        }

        System.out.println("\n");
        categoriaRepository.findAll().forEach(c -> {
            System.out.println("Categoria: " + c.getNome() + " ID: " + c.getId());
        });

        System.out.println("Informa o nome da categoria:");
        var nomeCategoria2 = leitura.nextLine();
        Optional<Categoria> categoriaConsultada2 = categoriaRepository.findByNomeContainingIgnoreCase(nomeCategoria2);
        Long idCategoria = null;
        if (categoriaConsultada2.isPresent()) {
            idCategoria = categoriaConsultada2.get().getId();
        } else {
            System.out.println("\n*** Categoria não encontrada! Pressione qualquer tecla para sair...");
            var sair = leitura.nextLine();
            System.exit(0);
        }

        Categoria categoria = new Categoria();
        categoria.setId(idCategoria);
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(idFornecedor);
        Produto produto = new Produto(nomeProduto, Double.valueOf(valorProduto), categoria, fornecedor);
        produtoRepository.saveAll(List.of(produto));
        //System.exit(0);
    }

    private void consultarNomefornecedor(String nomeFornecedor) {
        Boolean saida = false;
        while (!saida) {
            Optional<Fornecedor> fornecedorConsultado = fornecedorRepository.findByNomeContainingIgnoreCase(nomeFornecedor);
            if (fornecedorConsultado.isPresent()) {
                saida = true;
            } else {
                System.out.println("Fornecedor não encontrado!");
            }
        }
    }

    private void cadastrarPedido() {
        produtoRepository.findAll().forEach(p -> {
            System.out.println("ID Produto: " + p.getId() +  " Nome Produto: " + p.getNome());
        });

        Boolean saida = false;
        while (!saida) {
            System.out.println("Informa o id do produto:");
            var idProduto = leitura.nextLine();

            Optional<Produto> idProdutoConsultado = produtoRepository.findById(Integer.valueOf(idProduto)) ;
            if (idProdutoConsultado.isPresent()) {
                Produto produto = new Produto();
                produto.setId(Long.valueOf(idProduto));
                Pedido pedido1 = new Pedido((Long.valueOf(idProduto)), LocalDate.now());
                pedido1.setProdutos(List.of(produto));

                pedidoRepository.saveAll(List.of(pedido1));

                System.out.println("\nPedido cadastrado com sucesso");

                saida = true;
            } else {
                System.out.println("Produto não encontrado!");
            }
        }
    }

    private void consultarPedidos() {
        pedidoRepository.findAll().forEach(p -> {
            System.out.println("\nID Pedido: " + p.getId() + " Data: " + p.getData() +  " Id Produto: " + p.getProdutos().toString());
        });
    }

    //11 - Consultar produto por nome (exato)
    private void consultaPprodutosPeloNome() {
        System.out.println("Informa o nome do produto:");
        var nomeProduto = leitura.nextLine();
        var produto = produtoRepository.findByNome(nomeProduto);
        System.out.println("\nProduto consultado (via toString): " + produto);
        produto.forEach(System.out::println);

        List<Produto> produtos = produtoRepository.findByNome(nomeProduto);
        produtos.forEach(p ->
                System.out.println("Lista da consulta do produto (via List<Produto> /  Stream): " + p.getNome() + " Preço: " + p.getPreco())
        );
    }

    //12 - Consultar produtos associados a uma categoria específica
    private void consultaPprodutosPelCategoria() {
        System.out.println("Informa o nome da categoria");
        var categoriaNome = leitura.nextLine();
        var produtosPorCategoria = produtoRepository.findByCategoriaNome(categoriaNome);
        System.out.println("\n Produto(s) da categoria: " + categoriaNome + " " +  produtosPorCategoria);
        //produtosPorCategoria
        produtosPorCategoria.forEach(System.out::println);
        System.out.println("\n");
    }

    //13 - Consulta produtos com preço maior que o valor fornecido.
    private void produtosComPrecoMaiorQueValorFornecido() {
        System.out.println("Informe o preço do produto maior do que o valor  pesquisado: ");
        var preco = leitura.nextDouble();
        List<Produto> produtos =  produtoRepository.findByPrecoGreaterThan(preco);
        System.out.println("\nProdutos com o preço maior que: " + preco  + ".");
        produtos.forEach(p ->
                        System.out.println("Nome Produto: " + p.getNome() + " Preço: " + p.getPreco())
                );
    }

    //14 - Consulta produtos com preço menor que o valor fornecido.
    private void produtosComPrecoMnorQueValorFornecido() {
        System.out.println("Informe o preço do produto maior do que o valor  pesquisado: ");
        var preco = leitura.nextDouble();
        List<Produto> produtos =  produtoRepository.findByPrecoLessThan(preco);
        System.out.println("\nProdutos com o preço menor que: " + preco  + ".");
        produtos.forEach(p ->
                System.out.println("Nome Produto: " + p.getNome() + " Preço: " + p.getPreco())
        );
        pausaTela();
    }

    //15 - Consulta produtos cujo nome contenha o termo especificado.
    private void produtosComNomeContido() {
        System.out.println("Informa uma parte do nome contido no produto:");
        var nomeProduto = leitura.nextLine();

        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nomeProduto);

        System.out.println("\nProdutos contidos no nome: " + produtos  + ".");
        produtos.forEach(p ->
                System.out.println("Nome Produto: " + p.getNome() + " Preço: " + p.getPreco())
        );
        pausaTela();
    }

    //16 - Consulta pedidos que ainda não possuem uma data de entrega.
    private void consultaPedidosSemDataDeEntrega() {
        List<Pedido> pedidos = pedidoRepository.findByDataEntregaIsNull();
        System.out.println("\nPedidos sem data de entrega: ");
        pedidos.forEach(p ->
            System.out.println("Pedido: " + p.getId() + " Data Pedido: " + p.getData())
        );
        pausaTela();
    }

    //17 - Consulta pedidos com data de entrega preenchida.
    private void consultaPedidosComDataDeEntrega() {
        List<Pedido> pedidos = pedidoRepository.findByDataEntregaIsNotNull();
        System.out.println("\nPedidos com data de entrega: ");
        pedidos.forEach(p ->
                System.out.println("Pedido: " + p.getId() + " Data Pedido: " + p.getData() + " Data de Entrega: " + p.getDataEntrega())
        );
        pausaTela();
    }

    //18 - Consulta produtos de uma categoria ordenados pelo preço de forma crescente.
    private void consultaProdutosDeUmaCategoriaOrdenadaPeloPreco() {
        System.out.println("Informe o nome da categoria");
        var categoriaNome = leitura.nextLine();
        System.out.println("Produtos de uma categoria na ordem do preço:");
        List<Produto> produtos = produtoRepository.findByCategoriaNomeOrderByPrecoAsc(categoriaNome);
        produtos.forEach(p ->
                System.out.println("Produto: " + p.getNome() + " Preço: " + p.getPreco())
        );
        pausaTela();
    }

    //19 - Consulta produtos de uma categoria ordenados pelo preço de forma decrescente.
    private void consultaProdutosDeUmaCategoriaOrdenadaPeloPrecoDecrescente() {
        System.out.println("Informe o nome da categoria");
        var categoriaNome = leitura.nextLine();
        System.out.println("Produtos de uma categoria na ordem do preço decrescente:");
        List<Produto> produtos = produtoRepository.findByCategoriaNomeOrderByPrecoDesc(categoriaNome);
        produtos.forEach(p ->
                System.out.println("Produto: " + p.getNome() + " Preço: " + p.getPreco())
        );
        pausaTela();
    }

    //20 - Consulta a contagem de produtos em uma categoria específica.
    private void consultaContagemProdutosEemUmaCategoria() {
        System.out.println("Informa o nome da categoria:");
        var categoriaNome = leitura.nextLine();
        var qtdProdutos = produtoRepository.countByCategoriaNome(categoriaNome);
        System.out.println("\n Qtd. Produto(s) na categoria " + categoriaNome + ": " + qtdProdutos);
        pausaTela();
    }

    //21 - Consulta a contagem de produtos cujo preço seja maior que o valor fornecido.
    private void consultaContagemProdutosPprecoMaiorQueValorFornecido() {
        System.out.println("Informa o preço para contagem  de produtos:");
        var preco = leitura.nextDouble();
        var qtdProdutos = produtoRepository.countByPrecoGreaterThan( Double.valueOf(preco));
        System.out.println("\n Qtd. Produto(s) com preço maior que R$: " + preco + ": " + qtdProdutos);

        pausaTela();
    }

    //22 - Retorne produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado.
    private void produtosComPrecoMenorQueValorOuNomeContido() {
        System.out.println("\nInforma parte do nome do produto:");
        var nomeProduto = leitura.nextLine();

        System.out.println("\nInforma o preço do produto:");
        var preco = leitura.nextDouble();
        System.out.println("\nProdutos com preço inferior a " + preco  +  " ou produto contido em: " +  nomeProduto);
        var produtos =  produtoRepository.findByPrecoLessThanOrNomeContaining(preco, nomeProduto);
        produtos.forEach(p ->
                System.out.println("Produto: " + p.getNome() + " | preço: "  + p.getPreco())
        );
        pausaTela();
    }

    //23 - Retorne pedidos feitos após uma data específica.
    private void pedidosAposDataEspecífica() {
        System.out.println("\nInforma a data do pedido (formato yyyy-mm-dd):");
        var dataPedido = leitura.nextLine();
        LocalDate data = LocalDate.parse(dataPedido); //Date.valueOf(dataPedido).toLocalDate() ;
        System.out.println("\nPedidos gerados após a data: "  + dataPedido + " Data convertida: " + data);
        var pedidos = pedidoRepository.findByDataAfter(data);
        pedidos.forEach(p ->
                       System.out.println("Pedido: " + p.getId() +  " data inclusão: " + p.getData())
                );
        pausaTela();
    }

    //24 - Retorne pedidos feitos antes de uma data específica.
    private void pedidosAntesDaDataEspecífica() {
        System.out.println("\nInforma a data  do pedido (formato yyyy-mm-dd):");
        var dataPedido = leitura.nextLine();
        LocalDate data = LocalDate.parse(dataPedido); //Date.valueOf(dataPedido).toLocalDate() ;
        System.out.println("\nPedidos gerados antes da data: "  + dataPedido + " Data convertida: " + data);
        var pedidos = pedidoRepository.findByDataBefore(data);
        pedidos.forEach(p ->
                System.out.println("Pedido: " + p.getId() +  " data inclusão: " + p.getData())
        );
        pausaTela();
    }

    //25 - Retorne pedidos feitos em um intervalo de datas.
    private void pedidosEntre2Datas() {
        System.out.println("\nInforma a data inicio do pedido (formato yyyy-mm-dd):");
        var dataPedidoIni = leitura.nextLine();

        System.out.println("\nInforma a data final do pedido (formato yyyy-mm-dd):");
        var dataPedidoFim = leitura.nextLine();

        LocalDate dataIni = LocalDate.parse(dataPedidoIni);
        LocalDate dataFim = LocalDate.parse(dataPedidoFim);

        System.out.println("\nPedidos gerados na data de: "  + dataIni + " até: " + dataFim);
        var pedidos = pedidoRepository.findByDataBetween(dataIni, dataFim);
        pedidos.forEach(p ->
                System.out.println("Pedido: " + p.getId() +  " data inclusão: " + p.getData())
        );
        pausaTela();
    }

    //26 - Retorne os três produtos mais caros.
    private void listaDosTresProdutosNaisCaros() {
        System.out.println("\n3 Produtos mais caros na ordem decrescente:");
        var top3 = produtoRepository.findTop3ByOrderByPrecoDesc();
            top3.forEach(p  ->
                    System.out.println("Produto: " +  p.getNome() + " Preço: " +  p.getPreco())
            );

        pausaTela();
    }

    //27 - Retorne os cinco produtos mais baratos de uma categoria.
    private void listaDosCincoProdutosNaisBaratos() {
        System.out.println("\n3 Produtos mais baratos na ordem ecrescente:");
        var top3 = produtoRepository.findTop3ByOrderByPrecoAsc();
        top3.forEach(p  ->
                System.out.println("Produto: " +  p.getNome() + " Preço: " +  p.getPreco())
        );
        pausaTela();
    }

    private void testeMetodo() {

        Date dData = null;
        //dData = DataUtil.getData("0001-01-01"); //  Value.o "25/12/2025";
        dData = DataUtil.getData("2025-10-31T07:06:24"); //  Value.o "25/12/2025";

        System.out.println("\nRetorno da variavel dData: " + dData);

        //DataUtil dataUtil  = new DataUtil();
        //Date data2 = new Date(); //= D  LocalDate.now();
        //data2  =  Value.toString() ;
        //String data5 = LocalDate.now();
        //data2 =  data3;
        var sData6  =  LocalDate.now();
        //String sData = "0001-01-01";
        //String.valueOf(LocalDate.now());
        //var sData2 = DataUtil.getData(sData);
        //DataUtil dData2 = getData(sData);
        //System.out.println("Data Retornada: "  + dData2);
        //var dDataSemHora = getDataSemHora(data2);
        //2025-10-31T07:06:24
        //String sDataHora =  getDataHora(dData, "T07:06:24"); // getDataHora(sData6);

        //System.out.println("RetornoDataUtil.getDataHora(): " + sDataHora);

        pausaTela();

    }
}
