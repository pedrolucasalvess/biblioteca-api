package com.biblioteca.api.repository;

import com.biblioteca.api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interface de acesso a dados para a entidade Livro
// Herda métodos CRUD do JpaRepository automaticamente
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
