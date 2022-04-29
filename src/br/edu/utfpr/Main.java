package br.edu.utfpr;

import br.edu.utfpr.model.Carro;
import br.edu.utfpr.model.CarroEstacionamento;
import br.edu.utfpr.model.Estacionamento;
import br.edu.utfpr.service.CRUD.CalculoMultaService;
import br.edu.utfpr.service.CRUD.CarroCRUDService;
import br.edu.utfpr.service.CRUD.EstacionamentoCRUDService;
import br.edu.utfpr.service.CRUD.EstacionarCRUDService;
import br.edu.utfpr.service.CRUD.impl.CalculoMultaServiceImpl;
import br.edu.utfpr.service.CRUD.impl.CarroCRUDServiceImpl;
import br.edu.utfpr.service.CRUD.impl.EstacionamentoCRUDServiceImpl;
import br.edu.utfpr.service.CRUD.impl.EstacionarCrudServiceImpl;
import br.edu.utfpr.sql.TableControl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static <Int> void main(String[] args) {
        EstacionamentoCRUDService estacionamentoService = new EstacionamentoCRUDServiceImpl();
        CarroCRUDService carroService = new CarroCRUDServiceImpl();
        EstacionarCRUDService estacionarService = new EstacionarCrudServiceImpl();

        TableControl.createTablesV1();

        Estacionamento estacionamento = new Estacionamento(1, "Rua 29 de abril", 5.50);
        System.out.println(estacionamento);
        estacionamentoService.salvarEstacionamento(estacionamento);
        int idEstacionamento = estacionamento.getId();

        Carro carro1 = new Carro(1,"Golf GTE", "VW", "ABC1234");
        Carro carro2 = new Carro(2,"350z", "Nissan", "DEF5678");
        carroService.salvarCarro(carro1);
        carroService.salvarCarro(carro2);

        LocalDateTime currentDateTime = LocalDateTime.now();

        //diminuindo as horas ao salvar para simular a multa
        CarroEstacionamento carroEstacionado1 = estacionarService.addCarroNoEstacionamento(carro1.getId(), idEstacionamento, "A10", currentDateTime.minusHours(2));
        CarroEstacionamento carroEstacionado2 = estacionarService.addCarroNoEstacionamento(carro2.getId(), idEstacionamento, "A20", currentDateTime.minusHours(3));

        System.out.println("===========================");
        System.out.println("CARRO ESTACIONADO");
        System.out.println("===========================");
        System.out.println(carroEstacionado1);
        System.out.println(carroEstacionado2);

        //CaroEstacionado1 saindo do estacionamento
        carroEstacionado1 = estacionarService.removeCarroDoEstacionamento(carroEstacionado1, currentDateTime);

        System.out.println("===========================");
        System.out.println("CARRO SAINDO");
        System.out.println("===========================");
        System.out.println(carroEstacionado1);

        CalculoMultaService multaService = new CalculoMultaServiceImpl();
        Double multaCarro1 = multaService.valorMultaCaldulado(estacionamento, carroEstacionado1.getDataEntrada(), carroEstacionado1.getDataSaida());
        System.out.println("===========================");
        System.out.println("MULTA CALCULADA PARA CARRO ID:" + carroEstacionado1.getIdCarro());
        System.out.println("===========================");
        System.out.println(multaCarro1);
    }
}
