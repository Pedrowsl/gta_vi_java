package br.com.fatec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {
    public static boolean cadastrar(Partida p) {
        Connection conexao = null;
        try {
            //1. Obter a conexão com o banco de dados
            conexao = ConnectionFactory.obterConexao();
            //2. Definir o comando SQL
            var sql = "INSERT INTO tb_partida (jogador, pontuacao) VALUES (?, ?)";
            //3. Preparar o comando SQL
            PreparedStatement ps = conexao.prepareStatement(sql);
            //4. Substituir os eventuais placeholders
            ps.setString(1, p.getJogador());
            ps.setInt(2, p.getPontuacao());
            //5. Executar o comando SQL
            ps.execute();
            //6. Fechar a conexão com o banco de dados
            ps.close();
            conexao.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static List<Partida> listar() {
        var partidas = new ArrayList<Partida>();
        Connection conexao = null;
        try {
            //1. Estabelecer canal de comunicação com o banco
            conexao = ConnectionFactory.obterConexao();
            //2. Definir o comando SQL
            var sql = "SELECT * FROM tb_partida";
            //3. Preparar o canal
            var ps = conexao.prepareStatement(sql);
            //4. Executar o comando
            ResultSet rs = ps.executeQuery();
            //5. Tratar os dados capturados junto ao banco
            while (rs.next()) {
                var codigo = rs.getInt("codigo");
                var jogador = rs.getString("jogador");
                var pontuacao = rs.getInt("pontuacao");

                var partida = Partida.builder()
                    .codigo(codigo)
                    .jogador(jogador)
                    .pontuacao(pontuacao)
                    .build();

                partidas.add(partida);
            }
            //6. Fechar os canais de comunicação
            rs.close();
            ps.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return partidas;
    }
}
