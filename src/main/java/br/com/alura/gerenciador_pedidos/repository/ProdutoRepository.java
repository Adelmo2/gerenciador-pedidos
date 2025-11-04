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
    List<Produto> produtosQueComecemComUmaLetraEspecífica(String letra);
}
//Inicio para os repositorios a para o prinipal 3
////Final para os repositorios a para o prinipal 3
