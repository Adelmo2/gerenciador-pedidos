package br.com.alura.gerenciador_pedidos;

import br.com.alura.gerenciador_pedidos.principal.Principal;
import br.com.alura.gerenciador_pedidos.principal.Principal2;
import br.com.alura.gerenciador_pedidos.principal.Principal3;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {

    private final Principal principal;

    private final Principal2 principal2;

    private final Principal3 principal3;

    public GerenciadorPedidosApplication(Principal principal, Principal2 principal2, Principal3 principal3) {
        this.principal = principal;
        this.principal2 = principal2;
        this.principal3 = principal3;
    }

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorPedidosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Principal principal = new Principal(); //nao estanciar quando estiver utilizando o jpa
        //principal.principal();
        //principal2.CadastrosEConsultas();
        principal3.CadastrosEConsultas3();
    }
}
