package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findById(Integer id);

    List<Produto> findByNome(String nome);

    List<Produto> findByCategoriaNome(String categoriaNome);

    List<Produto> findByPrecoGreaterThan(double preco);

    List<Produto> findByPrecoLessThan(double preco);

    List<Produto> findByNomeContainingIgnoreCase(String nomeProduto);

    List<Produto> findByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

    List<Produto> findByCategoriaNomeOrderByPrecoDesc(String categoriaNome);

    long countByCategoriaNome(String categoriaNome);

    int countByPrecoGreaterThan(double preco);

    List<Produto> findByPrecoLessThanOrNomeContaining(Double preco, String termo);

    List<Produto> findTop3ByOrderByPrecoDesc();

    List<Produto> findTop3ByOrderByPrecoAsc();


    @Query("SELECT p FROM Produto p WHERE p.preco >= :valorProduto")
    List<Produto> produtosComPrecoMaiorQueUmValor(double valorProduto);

    @Query("SELECT p FROM Produto p ORDER BY p.preco ASC")
    List<Produto> produtosOrdenadosPeloPrecoCrescente();

    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> produtosOrdenadosPeloPrecoDecrescente();

    @Query("SELECT p FROM Produto p WHERE p.nome LIKE :letra%")
    List<Produto> produtosQueIniciamComUmaLetraEspecífica(String letra);

    @Query("SELECT AVG(p.preco) FROM Produto p")
    Double mediaDePrecoPorProduto();

    @Query("SELECT MAX(p.preco) FROM Produto p WHERE p.categoria.nome = :nomeCategoria")
    Double precoMmaximoDeUmProdutoEmUmaCategoria(String nomeCategoria);

    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome")
    List<Object[]> quantidadeDeProdutosPorCategoria();

    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome HAVING COUNT(p) > :qtdProdutos")
    List<Object[]> categoriasComMaisDeXProdutos(int qtdProdutos);

    //@Query("SELECT p.nome, c.nome FROM Produto p JOIN p.categoria c WHERE (p.nome = :nome OR c.nome = :nome)")
    //List<Produto> produtosFiltradosPorNomeOuPorCategoria(String nome);
    ///@Query("SELECT p.nome, c.nome, p.preco FROM Produto p JOIN p.categoria c WHERE (p.nome LIKE %:nome% OR c.nome LIKE %:nome%)")
    @Query("SELECT ('Produto: ' || p.nome), (' Categoria: ' || c.nome), (' Preço: ' || p.preco) FROM Produto p JOIN p.categoria c WHERE (LOWER(p.nome) LIKE %:nome% OR LOWER(c.nome) LIKE %:nome%)")
    List<Object[]> produtosFiltradosPorNomeOuPorCategoria(String nome);

    @Query(value = "SELECT * FROM produto ORDER BY preco DESC LIMIT 5", nativeQuery = true)
    List<Produto> cincoProdutosMaisCaros();
}

//Outras Indicações do instrutor.
//@Query(value = "SELECT * FROM produto ORDER BY preco DESC LIMIT 5", nativeQuery = true)
//List<Produto> buscarTop5ProdutosMaisCaros();

//@Query("SELECT p FROM Produto p WHERE (:nome IS NULL OR p.nome = :nome) AND (:categoria IS NULL OR p.categoria.nome = :categoria)")
//List<Produto> buscarProdutosFiltrados(@Param("nome") String nome, @Param("categoria") String categoria);

