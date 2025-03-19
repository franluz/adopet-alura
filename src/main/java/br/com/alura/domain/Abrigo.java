package br.com.alura.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Abrigo {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;
    public Abrigo(String nome, String telefone, String email) {
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
    }
public void setId(Long id){
        this.id=id;
}

}
