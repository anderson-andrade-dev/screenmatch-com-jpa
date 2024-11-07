package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Anderson Andrade Dev
 * @Data de Criação 06/11/2024
 */
public class ConsultaChatGPT {
    @Value("${openiaApiKey}")
    private static String apiKey;
    public static String obterTraducao(String texto) {

        //Pegando direto da variavel de ambiente
        // OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));
        OpenAiService service = new OpenAiService(apiKey);
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: %s".formatted(texto))
                .maxTokens(1000)
                .temperature(0.7)
                .build();
        var resposta = service.createCompletion(request);
        return resposta.getChoices().get(0).getText();
    }
}
