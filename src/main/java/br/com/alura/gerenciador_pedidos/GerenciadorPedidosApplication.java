package br.com.alura.gerenciador_pedidos;

import br.com.alura.gerenciador_pedidos.principal.Principal;
import br.com.alura.gerenciador_pedidos.principal.Principal2;
import br.com.alura.gerenciador_pedidos.principal.Principal3;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {

    private Scanner leitura = new Scanner(System.in);

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
        //principal3.CadastrosEConsultas3();
        Menu();
    }

    private void Menu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    0  - Sair
                    1  - Cadastros e Consultas
                    2  - Consultas
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    principal2.CadastrosEConsultas();
                    break;
                case 2:
                    principal3.CadastrosEConsultas3();
                    break;
                case 0:
                    System.out.println("Saindo do menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
