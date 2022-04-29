package br.edu.utfpr.service.CRUD.impl;

import br.edu.utfpr.model.Carro;
import br.edu.utfpr.repository.CarroRepository;
import br.edu.utfpr.service.CRUD.CarroCRUDService;

import java.util.List;

public class CarroCRUDServiceImpl implements CarroCRUDService {

    CarroRepository repository = new CarroRepository();

    @Override
    public Carro salvarCarro(Carro carro) {
        repository.salvar(carro);
        return carro;
    }

    @Override
    public List<Carro> buscarTodos() {
        var retorno= repository.buscarTodos();
        return retorno;
    }

    @Override
    public boolean removerCarro(Carro carro) {
        var retorno = repository.remover(carro);
        return retorno;
    }

    @Override
    public boolean removerCarro(int idCarro) {
        var retorno = repository.remover(idCarro);
        return retorno;
    }

//    @Override
//    public List<Carro> buscaPorDisciplina(Disciplina disciplina) {
//        log(INFO, "Iniciar busca de alunos da disciplina " + disciplina.getNome());
//        var retorno = repository.buscarPorDisciplina(disciplina.getId());
//        log(INFO, "finalizou busca alunos");
//        return retorno;
//    }
}
