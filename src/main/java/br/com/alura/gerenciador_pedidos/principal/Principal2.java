package br.com.alura.gerenciador_pedidos.principal;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Fornecedor;
import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRepository;
import br.com.alura.gerenciador_pedidos.repository.FornecedorRepository;
import br.com.alura.gerenciador_pedidos.repository.PedidoRepository;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRepository;
import com.sun.jdi.IntegerValue;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
    public void CadastrarForncedor() {
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
                    opcao = 0;
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
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
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
        Produto produto = new Produto();
        pedidoRepository.findAll().forEach(p -> {
            var idProdutoConsultado = produtoRepository.findById(Integer.valueOf( p.getProdutos() )) ;
            System.out.println("\nID Pedido: " + p.getId() + " Data: " + p.getData() +  " Id Produto: " + p.getProdutos().toString());
        });

    }

}
