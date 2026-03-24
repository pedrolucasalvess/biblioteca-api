package com.biblioteca.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

// @Entity indica que esta classe é uma entidade JPA (tabela no banco de dados)
@Entity
// @Table define o nome da tabela no banco de dados
@Table(name = "autores")
public class Autor {

    // @Id marca este campo como chave primária
    // @GeneratedValue configura auto-incremento para gerar IDs automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank valida que o campo não pode ser vazio ou nulo
    // @Size define tamanho mínimo e máximo para o campo
    @NotBlank(message = "O nome do autor é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "A nacionalidade é obrigatória")
    @Size(min = 2, max = 50, message = "A nacionalidade deve ter entre 2 e 50 caracteres")
    private String nacionalidade;

    // @OneToMany: Um autor pode ter vários livros (relacionamento 1:N)
    // cascade = CascadeType.ALL: Quando deletar o autor, deleta todos os livros dele
    // orphanRemoval = true: Remove livros que ficarem sem autor
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livros;

    // Construtor vazio necessário para o JPA
    public Autor() {}

    public Autor(String nome, String nacionalidade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    // Getters e Setters
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
