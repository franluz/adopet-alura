package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AbrigoServiceTest {
    private ClientHttpConfiguration clientHttpConfiguration = mock(ClientHttpConfiguration.class);
    private AbrigoService abrigoService = new AbrigoService(clientHttpConfiguration);
    private PetService petService = new PetService(clientHttpConfiguration);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Abrigo abrigo = new Abrigo("Teste", "123456789", "francielle.luz@gmail.com");

    @Test
    public void deveVerificarQuandoHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0l);
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdENome = "0 - Teste";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[" + new Gson().toJson(abrigo) + "]");
        when(clientHttpConfiguration.dispararRequisicaoGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdEnome = lines[1];
        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectedIdENome, actualIdEnome);

    }

    @Test
    public void deveVerificarQuandoNaoHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0l);
        String expectedNaoAbrigosCadastrados = "Nao ha abrigos cadastrados";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(clientHttpConfiguration.dispararRequisicaoGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigo();
        String[] lines = baos.toString().split(System.lineSeparator());
        String actualNaoAbrigosCadastrados = lines[0];
        Assertions.assertEquals(expectedNaoAbrigosCadastrados, actualNaoAbrigosCadastrados);


    }
    @Test
    public void deveVerificarSeDispararRequisicaoPostSeraChamado() throws IOException, InterruptedException {
        String userInput = String.format("Test\npets.csv",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());

        System.setIn(bais);
        when(clientHttpConfiguration.
                disparaRequisicaoPost(anyString(), any())).thenReturn(response);

        petService.importarPetsDoAbrigo();
        verify(clientHttpConfiguration.disparaRequisicaoPost(anyString(), anyString()), atLeast(1));
    }
}
