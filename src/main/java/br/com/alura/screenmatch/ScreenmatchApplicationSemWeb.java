//package br.com.alura.screenmatch;
//
//import br.com.alura.screenmatch.principal.Principal;
//import br.com.alura.screenmatch.repository.SerieRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenmatchApplicationSemWeb implements CommandLineRunner {
//
//    private final SerieRepository repository;
//
//    public ScreenmatchApplicationSemWeb(SerieRepository repository) {
//        this.repository = repository;
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(ScreenmatchApplicationSemWeb.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Principal principal = new Principal(repository);
//        principal.exibeMenu();
//    }
//}
