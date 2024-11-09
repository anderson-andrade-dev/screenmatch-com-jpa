package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dtos.SerieDTO;
import br.com.alura.screenmatch.service.SerieServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Anderson Andrade Dev
 * @Data de Criação 08/11/2024
 */
@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieServices service;

    public SerieController(SerieServices service) {
        this.service = service;
    }

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return service.todasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO>obterTop5Series(){
        return service.top5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO>obterLancamentos(){
        return service.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id){
       return service.obterPorId(id);
    }

}
