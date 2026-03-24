package com.biblioteca.api.repository;

import com.biblioteca.api.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository indica que esta interface é um componente de acesso a dados
// JpaRepository<Autor, Long> fornece métodos prontos para CRUD:
// - save(), findAll(), findById(), deleteById(), etc
// Autor = entidade que será manipulada
// Long = tipo da chave primária (ID)
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
