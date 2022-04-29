package br.edu.utfpr.repository;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.Carro;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class CarroRepository implements Repository<Carro> {

    @Override
    public List<Carro> buscarTodos() {
        Connection conn = ConnectDataBase.createConnections();
        List<Carro> retorno = new ArrayList<>();
        try {
            PreparedStatement psBuscar = conn.prepareStatement(
                    "SELECT * FROM aluno"
            );
            findAndBuild(conn, retorno, psBuscar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public Carro salvar(Carro carro) {
        Connection conn = ConnectDataBase.createConnections();
        try {
            PreparedStatement psSalvar = conn.prepareStatement(
                    "INSERT INTO carro(modelo, marca, placa) " +
                            "VALUES(?, ?, ?)", RETURN_GENERATED_KEYS
            );
            psSalvar.setString(1, carro.getModelo());
            psSalvar.setString(2, carro.getMarca());
            psSalvar.setString(3, carro.getPlaca());

            int linhasAfetadas = psSalvar.executeUpdate();
            ResultSet generatedKeys = psSalvar.getGeneratedKeys();

            if(linhasAfetadas == 0)
                System.out.printf("ERRO AO ADICIONAR CARRO PLACA: %s%n", carro.getPlaca().toUpperCase());
            else {
                if(generatedKeys.next())
                    carro.setId(generatedKeys.getInt(1));
            }
            psSalvar.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERRO AO CADASTRAR CARRO");
        }
        return carro;
    }

    @Override
    public Carro atualizar(Carro carro) {
        return null;
    }

    @Override
    public boolean remover(Carro carro) {
        remover(carro.getId());
        return false;
    }

    @Override
    public boolean remover(int i) {
        Connection conn = ConnectDataBase.createConnections();
        try {
            PreparedStatement psExcluir = conn.prepareStatement("" +
                    "DELETE FROM carro WHERE id=?"
            );
            psExcluir.setInt(1, i);
            int linhasAfetadas = psExcluir.executeUpdate();
            psExcluir.close();
            conn.close();

            return linhasAfetadas == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERRO AO REMOVER carro");
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

    private void findAndBuild(Connection conn, List<Carro> retorno, PreparedStatement psBuscar) throws SQLException {
        psBuscar.executeQuery();
        ResultSet resultSet = psBuscar.getResultSet();
        while (resultSet.next()) {
            Carro carro = Carro.builder()
                    .modelo(resultSet.getString(2))
                    .marca(resultSet.getString(3))
                    .placa(resultSet.getString(4))
                    .build();
            carro.setId(resultSet.getInt(1));
            retorno.add(carro);
        }
        psBuscar.close();
        conn.close();
    }

}
