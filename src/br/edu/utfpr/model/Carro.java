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
public class Carro extends Model{

    private int id;
    private String modelo;
    private String marca;
    private String placa;
//    private LocalDate dataNascimento;

    @Override
    public CreateTableHelper generateCreateTableSQL() {
        String createTable = "" +
                "CREATE TABLE IF NOT EXISTS carro (" +
                "id SERIAL PRIMARY KEY, " +
                "modelo VARCHAR(50) NOT NULL, " +
                "marca VARCHAR(20) NOT NULL, " +
                "placa VARCHAR(70) NOT NULL);";


        String dropTable = "" +
                "DROP TABLE IF EXISTS carro";

        return new CreateTableHelper(createTable, dropTable);

    }
}
