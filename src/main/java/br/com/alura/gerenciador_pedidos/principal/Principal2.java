package br.com.alura.gerenciador_pedidos.principal;

import br.com.alura.gerenciador_pedidos.model.Categoria;
import br.com.alura.gerenciador_pedidos.model.Fornecedor;
import br.com.alura.gerenciador_pedidos.model.Produto;
import br.com.alura.gerenciador_pedidos.repository.CategoriaRepository;
import br.com.alura.gerenciador_pedidos.repository.FornecedorRepository;
import br.com.alura.gerenciador_pedidos.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                    break;
                case 4:
                    consultarIdfornecedor();
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
        categoriaRepository.saveAll(List.of(categoria));

        System.out.println("\n******---> Catergorias Cadastradas:");
        categoriaRepository.findAll().forEach(c -> {
            System.out.println("Categoria: " + c.getNome());
        });
    }

    private void cadastrarProduto() {
        Categoria categoria = new Categoria();
        categoria.setId(7L);
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(7L);
        Produto produto = new Produto("Memoria", 300.00, categoria, fornecedor);
        produtoRepository.saveAll(List.of(produto));
    }

    private void consultarIdfornecedor() {
        System.out.println("Informa o nome do fornecedor:");
        var nomefornecedor = leitura.nextLine();

        Optional<Fornecedor> fornecedorConsultado = fornecedorRepository.findByNomeContainingIgnoreCase(nomefornecedor);

        if (fornecedorConsultado.isPresent()) {
            System.out.println("Fornecedor: " + fornecedorConsultado.get().getNome());
        } else {
            System.out.println("Fornecedor não encontrado!");
        }
//        fornecedorRepository.findAll().forEach(fornecedor -> {
//            fornecedor.getNome().contains(nomefornecedor);
//            //System.out.println(t);
//            //var idForncedro = fornecedor.getId();
//            //System.out.println(idForncedro);
//            //System.out.println(fornecedorRepository.'');
//        });
    }

}
