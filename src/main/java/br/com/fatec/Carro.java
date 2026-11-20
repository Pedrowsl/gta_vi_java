package br.com.fatec;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class Carro implements Comparable<Carro>{
    private String modelo;
    private int potencia;

    public int getTempoDeVolta(){
        return 30 + 6000/potencia;
    }

    public String toString(){
        return modelo + " (" + potencia + " cv)";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Carro)){
            return false;
        }

        Carro outro = (Carro) obj;
        return this.modelo.equals(outro.modelo);
    }

    @Override 
    public int compareTo(Carro outro){
        if(this.potencia != outro.potencia){
            return Integer.compare(outro.potencia, this.potencia);
        }
        return this.modelo.compareTo(outro.modelo);
    }
}