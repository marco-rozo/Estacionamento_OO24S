package br.edu.utfpr.service.CRUD;

import br.edu.utfpr.model.Estacionamento;

import java.time.LocalDateTime;

public interface CalculoMultaService {
    Double valorMultaCaldulado(Estacionamento estacionamento, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida);

}
