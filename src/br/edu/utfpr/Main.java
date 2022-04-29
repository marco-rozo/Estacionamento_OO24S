package br.edu.utfpr;

import br.edu.utfpr.model.Carro;
import br.edu.utfpr.model.Estacionamento;
import br.edu.utfpr.service.CRUD.CarroCRUDService;
import br.edu.utfpr.service.CRUD.EstacionamentoCRUDService;
import br.edu.utfpr.service.CRUD.EstacionarCRUDService;
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
        java.util.Date date = new Date();
//        DisciplinaCRUDService disciplinaService = new DisciplinaCrudServiceImpl();

        TableControl.createTablesV1();

        Estacionamento estacionamento = new Estacionamento(1, "Rua 29 de abril", 22.50);
        System.out.println(estacionamento);
        estacionamentoService.salvarEstacionamento(estacionamento);
        int idEstacionamento = estacionamento.getId();
        System.out.println(estacionamento);

        Carro carro1 = new Carro(1,"Golf GTE", "VW", "ABC1234");
        Carro carro2 = new Carro(2,"350z", "Nissan", "DEF5678");
        carroService.salvarCarro(carro1);
        carroService.salvarCarro(carro2);

        estacionarService.addCarroNoEstacionamento(carro1.getId(), idEstacionamento, "A10", LocalDateTime.now());
    }
}
