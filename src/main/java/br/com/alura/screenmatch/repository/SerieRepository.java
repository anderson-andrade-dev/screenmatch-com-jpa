package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anderson Andrade Dev
 * @Data de Criação 06/11/2024
 */
public interface SerieRepository extends JpaRepository<Serie,Long> {
}
