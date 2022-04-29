package br.edu.utfpr.service.CRUD.impl;

import br.edu.utfpr.model.Estacionamento;
import br.edu.utfpr.service.CRUD.CalculoMultaService;

import java.time.Duration;
import java.time.LocalDateTime;


public class CalculoMultaServiceImpl implements CalculoMultaService {

    @Override
    public Double valorMultaCaldulado(Estacionamento estacionamento, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida) {
        Double valorParaCalculo = estacionamento.getValorHora();
        long diff = Duration.between(dataHoraEntrada, dataHoraSaida).toHours();
        System.out.println("=======================");
        System.out.println("DIFERENÇA DA ENTRADA PARA SAIDA");
        System.out.println("=======================");
        System.out.println(diff);
        System.out.println("\n\n");

        Double diffHoras = Double.parseDouble(String.valueOf(diff));
        Double multa = diffHoras * valorParaCalculo;

        return multa;
    }
}
