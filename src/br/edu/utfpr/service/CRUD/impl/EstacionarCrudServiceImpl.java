package br.edu.utfpr.service.CRUD.impl;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.Carro;
import br.edu.utfpr.model.CarroEstacionamento;
import br.edu.utfpr.model.Estacionamento;
import br.edu.utfpr.service.CRUD.EstacionarCRUDService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class EstacionarCrudServiceImpl implements EstacionarCRUDService {

//    DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
//
//    @Override
//    public Disciplina salvarDisciplina(Disciplina disciplina) {
//        log(INFO, "Iniciou salvarDisciplina() " + disciplina.toString());
//        disciplinaRepository.salvar(disciplina);
//        log(INFO, "Finalizou salvar: " + disciplina.toString());
//        return disciplina;
//    }

    @Override
    public CarroEstacionamento addCarroNoEstacionamento(Carro carro, Estacionamento estacionamento, String vaga, LocalDateTime horaEstacionar) {
        return addCarroNoEstacionamento(carro.getId(), estacionamento.getId(), vaga, horaEstacionar);
    }

    @Override
    public CarroEstacionamento addCarroNoEstacionamento(int idCarro, int idEstacionamento, String vaga, LocalDateTime dataHoraEstacionar) {
        Connection conn = ConnectDataBase.createConnections();
        var retorno = CarroEstacionamento.builder()
                .idCarro(idCarro)
                .idEstacionamento(idEstacionamento)
                .vaga(vaga)
                .dataEntrada(dataHoraEstacionar)
                .build();
        try {
            PreparedStatement psInsert = conn.prepareStatement(
                    "INSERT INTO carro_estacionamento(id_carro, id_estacionamento, vaga, datahora_entrada) " +
                            "VALUES (?, ?, ?, ?)", RETURN_GENERATED_KEYS
            );
            psInsert.setInt(1, idCarro);
            psInsert.setInt(2, idEstacionamento);
            psInsert.setString(3, vaga);
            psInsert.setObject(4, dataHoraEstacionar);
            var linhasAfetadas = psInsert.executeUpdate();
            var resultSet = psInsert.getGeneratedKeys();
            if (linhasAfetadas < 1) {
                System.out.println("====================");
                System.out.println("Erro ao estacionar");
                System.out.println("====================");
            } else {
                if (resultSet.next()) {
                    retorno.setId(resultSet.getInt(1));
                }
            }
            psInsert.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("====================");
            System.out.println("Falha na inserção");
            System.out.println("====================");
            e.printStackTrace();
        }
        return retorno;
    }
}
