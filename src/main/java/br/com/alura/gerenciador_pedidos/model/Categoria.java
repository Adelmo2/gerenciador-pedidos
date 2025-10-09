package br.com.alura.gerenciador_pedidos.model;

<<<<<<< HEAD
import jakarta.persistence.*;
=======
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
>>>>>>> 471832b5b0a54825ec7f94b5575f998a840f55d1

import java.util.List;

@Entity
public class Categoria {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
>>>>>>> 471832b5b0a54825ec7f94b5575f998a840f55d1
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Produto> produtos;

<<<<<<< HEAD
=======
    //@Autowired
>>>>>>> 471832b5b0a54825ec7f94b5575f998a840f55d1
    public Categoria() {}

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
<<<<<<< HEAD
//
//    public void setProdutos(List<Produto> produtos) {
//        produtos.forEach(p -> p.setCategoria(this));
//        this.produtos = produtos;
//    }
=======

    public void setProdutos(List<Produto> produtos) {
        produtos.forEach(p -> p.setCategoria(this));
        this.produtos = produtos;
    }
>>>>>>> 471832b5b0a54825ec7f94b5575f998a840f55d1

}
