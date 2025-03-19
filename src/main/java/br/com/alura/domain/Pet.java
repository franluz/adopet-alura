package br.com.alura.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class Pet {
    private Long id;
    private String tipo;
    private String nome;
    private String raca;
    private Integer idade;
    private String cor;
    private Float peso;
    public Pet(String tipo,
               String nome,
               String raca,
               Integer idade
             ) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;

    }
    public Pet(String tipo,
               String nome,
               String raca,
               Integer idade,
               String cor,
               Float peso) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;
    }

    public Pet(long id, String tipo, String nome, String raca, int idade) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }


}
