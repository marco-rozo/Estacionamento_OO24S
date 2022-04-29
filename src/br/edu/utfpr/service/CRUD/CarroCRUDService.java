package br.edu.utfpr.service.CRUD;

import br.edu.utfpr.model.Carro;

import java.util.List;

public interface CarroCRUDService {

    List<Carro> buscarTodos();

    Carro salvarCarro(Carro carro);

    boolean removerCarro(Carro carro);

    boolean removerCarro(int idCarro);

//    List<Carro> buscaPorDisciplina(Disciplina disciplina);
}
