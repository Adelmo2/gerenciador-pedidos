package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Pedido;
import br.com.alura.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long> {
    List<Pedido> findByDataEntregaIsNull();

    List<Pedido> findByDataEntregaIsNotNull();
}
