package br.com.alura.gerenciador_pedidos;

import br.com.alura.gerenciador_pedidos.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {

    private final Principal principal;

    public GerenciadorPedidosApplication(Principal principal) {
        this.principal = principal;
    }

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorPedidosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
<<<<<<< HEAD
        //Principal principal = new Principal();
=======
        Principal principal = new Principal();
>>>>>>> 471832b5b0a54825ec7f94b5575f998a840f55d1
        principal.principal();
    }
}
