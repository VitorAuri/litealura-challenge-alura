package com.alura.literalura.repository;

import com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRepository extends JpaRepository<Livro, Long>{
    @Query("SELECT COUNT(l) FROM Livro l JOIN l.languages lang WHERE lang = :idioma")
    long countByLanguage(@Param("idioma") String idioma);
}
