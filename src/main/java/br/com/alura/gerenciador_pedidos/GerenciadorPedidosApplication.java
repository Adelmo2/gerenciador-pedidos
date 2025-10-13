package br.com.alura.gerenciador_pedidos;

import br.com.alura.gerenciador_pedidos.principal.Principal;
import br.com.alura.gerenciador_pedidos.principal.Principal2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {

    private final Principal principal;

    private final Principal2 principal2;

    public GerenciadorPedidosApplication(Principal principal, Principal2 principal2) {
        this.principal = principal;
        this.principal2 = principal2;
    }

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorPedidosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Principal principal = new Principal(); //nao estanciar quando estiver utilizando o jpa
<<<<<<< HEAD
        //principal.principal();
        principal2.CadastrarForncedor();
=======
        principal.principal();
>>>>>>> 7828a0b8283941bca7f6c39be7d3759c39ba63d8
    }
}
