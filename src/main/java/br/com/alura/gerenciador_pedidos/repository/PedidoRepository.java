package br.com.alura.gerenciador_pedidos.repository;

import br.com.alura.gerenciador_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long> {
    List<Pedido> findByDataEntregaIsNull();

    List<Pedido> findByDataEntregaIsNotNull();

    //Resposta do instrutor
    //List<Pedido> findByDataPedidoAfter(LocalDate data)
    List<Pedido> findByDataAfter(LocalDate data);

    List<Pedido> findByDataBefore(LocalDate data);

    List<Pedido>  findByDataBetween(LocalDate dataIni, LocalDate dataFim);

    //List<Pedido> pedidosCadastradosEntreDuasDatas(LocalDate dataIni, LocalDate dataFim);
    //@Query("SELECT p FROM Pedido p WHERE p.data >= :dataIni AND p.data <= :dataFim")
    @Query("SELECT p FROM Pedido p WHERE p.data BETWEEN :dataIni AND :dataFim")
    List<Pedido> pedidosCadastradosEntreDuasDatas(LocalDate dataIni, LocalDate dataFim); //{
}
