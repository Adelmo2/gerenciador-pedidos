package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Fornecedor;
import br.com.alura.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
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

}
