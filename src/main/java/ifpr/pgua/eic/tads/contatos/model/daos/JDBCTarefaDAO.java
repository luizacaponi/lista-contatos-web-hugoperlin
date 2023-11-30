package ifpr.pgua.eic.tads.contatos.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;

public class JDBCTarefaDAO implements TarefaDAO {

    private FabricaConexoes fabricaConexao;

    public JDBCTarefaDAO(FabricaConexoes fabricaConexao){
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Resultado<Tarefa> criar(Tarefa tarefa) {

        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con
                    .prepareStatement("INSERT INTO oo_contatos(nome,email,telefone) VALUES (?,?,?)");

            pstm.setInt(1, tarefa.getId());
            pstm.setString(2, tarefa.getTitulo());
            pstm.setString(3, tarefa.getDescricao());
            
            pstm.executeUpdate();

            con.close();
            return Resultado.sucesso("Tarefa cadastrada com sucesso!", tarefa);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<List<Tarefa>> listar() {
        ArrayList<Tarefa> lista = new ArrayList<>();
        try {
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM oo_contatos");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");

                Tarefa tarefa = new Tarefa (id, titulo, descricao);

                lista.add(tarefa);
            }
            con.close();
            return Resultado.sucesso("Contatos carregados", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    
    }

}
