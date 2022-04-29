package br.edu.utfpr.model;

import br.edu.utfpr.sql.CreateTableHelper;
import lombok.*;

import java.time.LocalDate;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class CarroEstacionamento extends Model{

    private int id;
    private int idCarro;
    private int idEstacionamento;
    private String nome;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    @Override
    public CreateTableHelper generateCreateTableSQL() {
        String createTable = "" +
                "CREATE TABLE IF NOT EXISTS carro_estacionamento (" +
                "id SERIAL PRIMARY KEY, " +
                "id_carro INT NOT NULL, " +
                "id_estacionamento INT NOT NULL, " +
                "nome VARCHAR(50) NOT NULL, " +
                "data_entrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                "data_saida TIMESTAMP);";


        String dropTable = "" +
                "DROP TABLE IF EXISTS carro_estacionamento";

        return new CreateTableHelper(createTable, dropTable);
    }
}
