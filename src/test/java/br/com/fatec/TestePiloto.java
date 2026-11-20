package br.com.fatec;

import java.util.Random;

public class TestePiloto {
    public static void main(String[] args){
        Piloto piloto1 = new Piloto("Lucia", new Carro("Grotti Cheetah", 500));
        Piloto piloto2 = new Piloto("Lucia", new Carro("Pegassi Infernus", 500));
        Random gerador = new Random();

        System.out.println(piloto1.toString());
        piloto1.correr(gerador, false);
        System.out.println("Tempo sem nitro: " + piloto1.getTempo() + ("s"));
        piloto1.correr(gerador, true);
        System.out.println("Tempo com nitro: " + piloto1.getTempo() + ("s"));
        piloto1.somarPontos(10);
        piloto1.registrarVitoria();
        System.out.println(piloto1.toString());
        System.out.println("Iguais? " + piloto1.equals(piloto2));
    }
}