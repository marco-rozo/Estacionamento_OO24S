package br.edu.utfpr.model;

import br.edu.utfpr.sql.CreateTableHelper;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class CarroEstacionamento extends Model{

    private int id;
    private int idCarro;
    private int idEstacionamento;
    private String vaga;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;

    @Override
    public CreateTableHelper generateCreateTableSQL() {
        String createTable = "" +
                "CREATE TABLE IF NOT EXISTS carro_estacionamento (" +
                "id SERIAL PRIMARY KEY, " +
                "id_carro INT NOT NULL, " +
                "id_estacionamento INT NOT NULL, " +
                "vaga VARCHAR(50) NOT NULL, " +
                "datahora_entrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                "datahora_saida TIMESTAMP);";


        String dropTable = "" +
                "DROP TABLE IF EXISTS carro_estacionamento";

        return new CreateTableHelper(createTable, dropTable);
    }
}
