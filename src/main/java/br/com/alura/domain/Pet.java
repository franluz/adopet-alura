package br.com.alura.domain;

import lombok.Builder;

@Builder
public class Pet {
    private Long id;
    private String tipo;
    private String nome;
    private String raca;
    private Integer idade;
    private String cor;
    private Float peso;

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", idade=" + idade +
                ", cor='" + cor + '\'' +
                ", peso=" + peso +
                '}';
    }
}
