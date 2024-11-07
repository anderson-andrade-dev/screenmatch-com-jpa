package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {

    private final SerieRepository repository;
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    List<DadosSerie> dadosSeries = new ArrayList<>();

    public Principal(SerieRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                     1 - Buscar séries
                     2 - Buscar episódios
                     3 - Listar séries buscadas
                     0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listarSeriesBuscadas() {
//        dadosSeries.stream()
//                .map(Serie::new)
//                .sorted(Comparator.comparing(Serie::getGenero))
//                .forEach(System.out::println);

        repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        //dadosSeries.add(dados);
        repository.save(new Serie(dados));
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine().replace(" ", "+");
        String url = "%s%s%s".formatted(ENDERECO, nomeSerie, API_KEY);
        var json = consumo.obterDados(url);
        return conversor.obterDados(json, DadosSerie.class);
    }

    private void buscarEpisodioPorSerie() {

        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();
        String url = "%s%s&season=%d%s";

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {

            url = url.formatted(ENDERECO, dadosSerie.titulo().replace(" ", "+"), i, API_KEY);
            var json = consumo.obterDados(url);

            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);

        }
        temporadas.forEach(System.out::println);
    }
}