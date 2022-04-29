package br.edu.utfpr.service.CRUD.impl;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.Carro;
import br.edu.utfpr.model.CarroEstacionamento;
import br.edu.utfpr.model.Estacionamento;
import br.edu.utfpr.service.CRUD.EstacionarCRUDService;

import java.sql.*;
import java.time.LocalDateTime;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class EstacionarCrudServiceImpl implements EstacionarCRUDService {

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

    @Override
    public CarroEstacionamento removeCarroDoEstacionamento(CarroEstacionamento estacionado, LocalDateTime dataHoraSaida)
    {
        return removeCarroDoEstacionamento(estacionado.getIdCarro(), estacionado.getIdEstacionamento(), estacionado.getVaga(), estacionado.getDataEntrada(), dataHoraSaida);
    }

    @Override
    public CarroEstacionamento removeCarroDoEstacionamento(int idCarroEstacionado, int idEstacionamento, String vaga, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida) {
        Connection conn = ConnectDataBase.createConnections();
        var retorno = CarroEstacionamento.builder()
                .idCarro(idCarroEstacionado)
                .idEstacionamento(idEstacionamento)
                .vaga(vaga)
                .dataEntrada(dataHoraEntrada)
                .dataSaida(dataHoraSaida)
                .build();

        try {
            PreparedStatement psUpdate = conn.prepareStatement(
                    "UPDATE carro_estacionamento" +
                            " SET datahora_saida = ?" +
                            " WHERE id_carro = ? " +
                            " AND id_estacionamento = ? " +
                            " AND vaga = ?", RETURN_GENERATED_KEYS);

            psUpdate.setObject(1, dataHoraSaida);
            psUpdate.setInt(2, idCarroEstacionado);
            psUpdate.setInt(3, idEstacionamento);
            psUpdate.setString(4, vaga);

            var linhasAfetadas = psUpdate.executeUpdate();

            var resultSet = psUpdate.getGeneratedKeys();
            if (linhasAfetadas < 1) {
                System.out.println("====================");
                System.out.println("Erro ao sair do estacionamento");
                System.out.println("====================");
            } else {
                if (resultSet.next()) {
                    retorno.setId(resultSet.getInt(1));
                }
            }
            psUpdate.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("====================");
            System.out.println("Falha na atualização");
            System.out.println("====================");
            e.printStackTrace();
        }
        return retorno;
    }
}
