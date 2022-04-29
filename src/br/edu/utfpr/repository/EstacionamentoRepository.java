package br.edu.utfpr.repository;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.Estacionamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class EstacionamentoRepository implements Repository<Estacionamento> {

    @Override
    public List<Estacionamento> buscarTodos() {
        Connection conn = ConnectDataBase.createConnections();
        List<Estacionamento> retorno = new ArrayList<>();
        try {
            PreparedStatement psBuscar = conn.prepareStatement(
                    "SELECT * FROM estacionamento"
            );
            findAndBuild(conn, retorno, psBuscar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public Estacionamento salvar(Estacionamento estacionamento) {
        Connection conn = ConnectDataBase.createConnections();
        try {
            PreparedStatement psSalvar = conn.prepareStatement(
                    "INSERT INTO estacionamento(enderco, valorHora) " +
                            "VALUES(?, ?)", RETURN_GENERATED_KEYS
            );
            psSalvar.setString(1, estacionamento.getEnderco());
            psSalvar.setDouble(2, estacionamento.getValorHora());

            int linhasAfetadas = psSalvar.executeUpdate();
            ResultSet generatedKeys = psSalvar.getGeneratedKeys();

            if(linhasAfetadas == 0)
                System.out.printf("ERRO AO ADICIONAR Estacionamento: %s%n", estacionamento.getId());
            else {
                if(generatedKeys.next())
                    estacionamento.setId(generatedKeys.getInt(1));
            }
            psSalvar.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERRO AO CADASTRAR Estacionamento");
        }
        return estacionamento;
    }

    @Override
    public Estacionamento atualizar(Estacionamento estacionamento) {
        return null;
    }

    @Override
    public boolean remover(Estacionamento estacionamento) {
        remover(estacionamento.getId());
        return false;
    }

    @Override
    public boolean remover(int i) {
        Connection conn = ConnectDataBase.createConnections();
        try {
            PreparedStatement psExcluir = conn.prepareStatement("" +
                    "DELETE FROM estacionamento WHERE id=?"
            );
            psExcluir.setInt(1, i);
            int linhasAfetadas = psExcluir.executeUpdate();
            psExcluir.close();
            conn.close();

            return linhasAfetadas == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERRO AO REMOVER estacionamento");
        }
        return false;
    }

//    public List<Carro> buscarPorDisciplina(Disciplina disciplina) {
//     return buscarPorDisciplina(disciplina.getId());
//    }
//
//    public List<Aluno> buscarPorDisciplina(int id) {
//        Connection conn = ConnectDataBase.createConnections();
//        List<Aluno> retorno = new ArrayList<>();
//        try {
//            PreparedStatement psBuscar = conn.prepareStatement(
//                    "SELECT a.* FROM aluno a " +
//                    "INNER JOIN aluno_disciplina d on d.id_disciplina = ?"
//            );
//            psBuscar.setInt(1, id);
//            findAndBuild(conn, retorno, psBuscar);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return retorno;
//    }

    private void findAndBuild(Connection conn, List<Estacionamento> retorno, PreparedStatement psBuscar) throws SQLException {
        psBuscar.executeQuery();
        ResultSet resultSet = psBuscar.getResultSet();
        while (resultSet.next()) {
            Estacionamento estacionamento = Estacionamento.builder()
                    .enderco(resultSet.getString(2))
                    .valorHora(resultSet.getDouble(3))
                    .build();
            estacionamento.setId(resultSet.getInt(1));
            retorno.add(estacionamento);
        }
        psBuscar.close();
        conn.close();
    }

}
