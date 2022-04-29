package br.edu.utfpr.service.CRUD;

import br.edu.utfpr.model.Carro;
import br.edu.utfpr.model.Estacionamento;

import java.util.List;

public interface EstacionamentoCRUDService {

    List<Estacionamento> buscarTodos();

    Estacionamento salvarEstacionamento(Estacionamento estacionamento);

    boolean removerEstacionamento(Estacionamento estacionamento);

    boolean removerEstacionamento(int idEstacionamento);

//    List<Carro> buscaPorDisciplina(Disciplina disciplina);
}
