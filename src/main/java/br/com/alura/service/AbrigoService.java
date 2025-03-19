package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class AbrigoService {
    private ClientHttpConfiguration clientHttpConfiguration;

    public AbrigoService(ClientHttpConfiguration config) {
        this.clientHttpConfiguration = config;
    }

    public void cadastrarAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();
        Abrigo abrigo = new Abrigo(nome, telefone, email);

        HttpResponse<String> response = this.clientHttpConfiguration.disparaRequisicaoPost("http://localhost:8080/abrigos", abrigo);
        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }

    public void listarAbrigo() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = this.clientHttpConfiguration.dispararRequisicaoGet(uri);
        String responseBody = response.body();
        Abrigo[] abrigos = new ObjectMapper().readValue(responseBody, Abrigo[].class);
        List<Abrigo> lAbrigo = Arrays.stream(abrigos).toList();
        if (lAbrigo.isEmpty()) {
            System.out.println("Nao ha abrigos cadastrados");
        }
        mostrarAbrigo(lAbrigo);
    }

    private void mostrarAbrigo(List<Abrigo> lAbrigo) {
        if (lAbrigo != null && !lAbrigo.isEmpty()) {
            System.out.println("Abrigos cadastrados:");

            for (Abrigo abrigo : lAbrigo) {
                long id = abrigo.getId();
                String nome = abrigo.getNome();
                //System.out.println(abrigo.toString());
                System.out.println(id + " - " + nome);
            }
        }
    }
}
