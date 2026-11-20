package br.com.fatec;

public class TesteConcessionaria {
    public static void main(String[] args){
        Concessionaria concessionaria1 = new Concessionaria();
        
        System.out.println("Carros em estoque: " + concessionaria1.restantes());
        System.out.println("Sorteou: " + concessionaria1.sortear());
        System.out.println("Sorteou: " + concessionaria1.sortear());
        System.out.println("Sorteou: " + concessionaria1.sortear());
        System.out.println("Carros no estoque: " + concessionaria1.restantes()); 
    }
}
