package br.com.fatec;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Builder
public class Partida {
    private int codigo;
    private String jogador;
    private int pontuacao;
}