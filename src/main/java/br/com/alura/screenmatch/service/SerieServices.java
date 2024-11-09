package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dtos.SerieDTO;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Anderson Andrade Dev
 * @Data de Criação 08/11/2024
 */
@Service
public class SerieServices {
    private final SerieRepository repository;

    public SerieServices(SerieRepository repository) {
        this.repository = repository;
    }

    public List<SerieDTO> todasSeries() {
        return converterDados(repository.findAll());
    }

    public List<SerieDTO> top5Series(){
        return converterDados(repository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos(){
        return converterDados(repository.encontrarEpisodiosMaisRecentes());
    }

    private List<SerieDTO> converterDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(),
                        s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .collect(Collectors.toUnmodifiableList());

    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
                    s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
        }
        return null;
    }
}
