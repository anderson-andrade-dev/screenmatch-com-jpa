package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dtos.SerieDTO;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anderson Andrade Dev
 * @Data de Criação 08/11/2024
 */
@RestController
public class SerieController {

    private final SerieRepository repository;

    public SerieController(SerieRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/series")
    public List<SerieDTO> obterSeries(){
        return repository.findAll()
                .stream()
                .map(s -> new SerieDTO(s.getId(),s.getTitulo(),s.getTotalTemporadas(),s.getAvaliacao(),s.getGenero(),s.getAtores(),s.getPoster(),s.getSinopse()))
                .collect(Collectors.toUnmodifiableList());
    }
}
