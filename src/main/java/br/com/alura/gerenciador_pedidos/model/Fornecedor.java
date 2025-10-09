package br.com.alura.gerenciador_pedidos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    //asr - 08/10/2025
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    public Fornecedor() {}

    public Fornecedor(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    //    public Fornecedor(String nome) {
//        this.nome = nome;
//    }

//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public List<Produto> getProdutos() {
//        return produtos;
//    }
//
//    public void setProdutos(List<Produto> produtos) {
//        produtos.forEach(p -> p.setFornecedor(this));
//        this.produtos = produtos;
//    }
}
