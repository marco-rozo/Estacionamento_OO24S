package br.edu.utfpr.model;

import br.edu.utfpr.sql.CreateTableHelper;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class Estacionamento extends Model{
    //Carro
    //Estacionamento
    //CarroEstacionamento

    private int id;
    private String enderco;
    private Double valorHora;

    @Override
    public CreateTableHelper generateCreateTableSQL() {
        String createTable = "" +
                "CREATE TABLE IF NOT EXISTS estacionamento (" +
                "id SERIAL PRIMARY KEY, " +
                "enderco VARCHAR(50) NOT NULL," +
                "valor_hora float8 NOT NULL);";


        String dropTable = "" +
                "DROP TABLE IF EXISTS estacionamento";

        return new CreateTableHelper(createTable, dropTable);
    }
}
