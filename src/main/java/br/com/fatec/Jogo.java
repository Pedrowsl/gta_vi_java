package br.com.fatec;

import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Jogo {
    private static final int CORRIDAS = 3;
    private static final int NITROS = 2;

    public static void main(String[] args) {
        int op;
        do {
            String escolha = JOptionPane.showInputDialog("1-Jogar\n2-Ranking\n0-Sair");
            if (escolha == null) break;
            try {
                op = Integer.parseInt(escolha);
            } catch (NumberFormatException e) {
                op = -1;
            }
            switch (op) {
                case 1:
                    jogarCampeonato();
                    break;
                case 2:
                    exibirRanking();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Ate a proxima!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida.");
            }
        } while (op != 0);
    }

    private static String numerar(List<Piloto> pilotos) {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < pilotos.size(); i++) {
            texto.append(i + 1).append(". ").append(pilotos.get(i)).append("\n");
        }
        return texto.toString();
    }

    private static void jogarCampeonato() {
        String nome = JOptionPane.showInputDialog("Nome do jogador?");
        if (nome == null) return;
        String escolha = JOptionPane.showInputDialog("1-Jason\n2-Lucia");
        if (escolha == null) return;
        String pilotoEscolhido;
        switch (escolha) {
            case "1": pilotoEscolhido = "Jason"; break;
            case "2": pilotoEscolhido = "Lucia"; break;
            default:
                JOptionPane.showMessageDialog(null, "Opcao invalida.");
                return;
        }
        Random gerador = new Random();
        Concessionaria concessionaria = new Concessionaria();
        Campeonato campeonato = new Campeonato();
        Piloto jogador = new Piloto(pilotoEscolhido, concessionaria.sortear());
        campeonato.inscrever(jogador);
        campeonato.inscrever(new Piloto("Raul", concessionaria.sortear()));
        campeonato.inscrever(new Piloto("Boobie", concessionaria.sortear()));
        campeonato.inscrever(new Piloto("Cal", concessionaria.sortear()));
        JOptionPane.showMessageDialog(null, "Seu carro: " + jogador.getCarro());
        int nitros = NITROS;

        for (int i = 1; i <= CORRIDAS; i++) {
            String mensagem = "Corrida " + i + "/" + CORRIDAS + "\n"
                + numerar(campeonato.getClassificacao()) + "\n"
                + "Usar nitro? 1-Sim 2-Nao (restam " + nitros + ")";
            String resposta = JOptionPane.showInputDialog(mensagem);
            boolean usarNitro = "1".equals(resposta) && nitros > 0;
            if ("1".equals(resposta) && nitros == 0) {
                JOptionPane.showMessageDialog(null, "Sem nitro restante");
            }
            if (usarNitro) nitros--;
            List<Piloto> chegada = campeonato.disputarCorrida(gerador, jogador, usarNitro);
            StringBuilder texto = new StringBuilder("Chegada\n");
            for (int j = 0; j < chegada.size(); j++) {
                Piloto piloto = chegada.get(j);
                texto.append(j + 1).append(". ").append(piloto.getNome())
                    .append(" - ").append(piloto.getTempo()).append("s\n");
            }
            JOptionPane.showMessageDialog(null, texto.toString());
        }

        List<Piloto> classificacao = campeonato.getClassificacao();
        int posicao = classificacao.indexOf(jogador) + 1;
        String resultado = numerar(classificacao);
        if (posicao == 1) resultado += "Campeao de Vice City!";
        else resultado += "Posicao final: " + posicao;
        JOptionPane.showMessageDialog(null, resultado);

        Partida partida = Partida.builder().jogador(nome).pontuacao(jogador.getPontos()).build();
        JOptionPane.showMessageDialog(null,
            PartidaDAO.cadastrar(partida) ? "Partida salva" : "Erro ao salvar a partida");
    }

    private static void exibirRanking() {
        List<Partida> partidas = PartidaDAO.listar();
        partidas.sort((a, b) -> Integer.compare(b.getPontuacao(), a.getPontuacao()));
        StringBuilder texto = new StringBuilder("RANKING\n");
        for (int i = 0; i < partidas.size() && i < 5; i++) {
            Partida partida = partidas.get(i);
            texto.append(i + 1).append(". ").append(partida.getJogador())
                .append(" - ").append(partida.getPontuacao()).append(" pontos\n");
        }
        JOptionPane.showMessageDialog(null, texto.toString());
    }
}