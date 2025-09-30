package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
