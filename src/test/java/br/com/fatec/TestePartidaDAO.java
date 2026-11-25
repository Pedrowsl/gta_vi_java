package br.com.fatec;

public class TestePartidaDAO {
    public static void main(String[] args) {
        Partida ana = Partida.builder().jogador("Ana").pontuacao(26).build();
        Partida bia = Partida.builder().jogador("Bia").pontuacao(13).build();
        System.out.println("Cadastrou Ana? " + PartidaDAO.cadastrar(ana));
        System.out.println("Cadastrou Bia? " + PartidaDAO.cadastrar(bia));
        for (Partida partida : PartidaDAO.listar()) {
            System.out.println(partida.getCodigo() + " - " + partida.getJogador() + " - " + partida.getPontuacao());
        }
    }
}