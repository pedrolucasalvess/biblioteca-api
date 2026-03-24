package com.biblioteca.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// @Entity define que esta classe será uma tabela no banco de dados
@Entity
@Table(name = "livros")
public class Livro {

    // Chave primária com auto-incremento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Validação: campo obrigatório com tamanho entre 2 e 200 caracteres
    @NotBlank(message = "O título do livro é obrigatório")
    @Size(min = 2, max = 200, message = "O título deve ter entre 2 e 200 caracteres")
    private String titulo;

    // @Min e @Max definem valores mínimo e máximo para números
    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1000, message = "Ano deve ser maior que 1000")
    @Max(value = 2100, message = "Ano deve ser menor que 2100")
    private Integer ano;

    // RELACIONAMENTO ENTRE TABELAS
    // @ManyToOne: Muitos livros podem ter um autor (relacionamento N:1)
    // @JoinColumn: Define a coluna de chave estrangeira (autor_id) na tabela livros
    // Isso cria o relacionamento entre as tabelas 'livros' e 'autores'
    @NotNull(message = "O autor é obrigatório")
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    // Construtor vazio obrigatório para o JPA
    public Livro() {}

    public Livro(String titulo, Integer ano, Autor autor) {
        this.titulo = titulo;
        this.ano = ano;
        this.autor = autor;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
