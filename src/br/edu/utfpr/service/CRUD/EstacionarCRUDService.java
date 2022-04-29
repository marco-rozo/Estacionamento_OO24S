package br.edu.utfpr.service.CRUD;

import br.edu.utfpr.model.Carro;
import br.edu.utfpr.model.CarroEstacionamento;
import br.edu.utfpr.model.Estacionamento;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface EstacionarCRUDService {
//    Disciplina salvarDisciplina(Disciplina disciplina);
    CarroEstacionamento addCarroNoEstacionamento(Carro carro, Estacionamento estacionamento, String vaga, LocalDateTime dataHoraEntrada);
    CarroEstacionamento addCarroNoEstacionamento(int idCarro, int idAEstacionamento, String vaga, LocalDateTime dataHoraEntrada);
}
