package br.com.alura.screenmatch.dtos;

import br.com.alura.screenmatch.enuns.Categoria;

/**
 * @author Anderson Andrade Dev
 * @Data de Criação 08/11/2024
 */
public record SerieDTO(Long id, String titulo, Integer totalTemporadas, Double avaliacao, Categoria genero, String atores, String poster, String sinopse) {
}
