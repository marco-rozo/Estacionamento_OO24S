package br.edu.utfpr.service.CRUD.impl;

import br.edu.utfpr.model.Estacionamento;
import br.edu.utfpr.repository.EstacionamentoRepository;
import br.edu.utfpr.service.CRUD.EstacionamentoCRUDService;

import java.util.List;

public class EstacionamentoCRUDServiceImpl implements EstacionamentoCRUDService {

    EstacionamentoRepository repository = new EstacionamentoRepository();

    @Override
    public Estacionamento salvarEstacionamento(Estacionamento estacionamento) {
        repository.salvar(estacionamento);
        return estacionamento;
    }

    @Override
    public List<Estacionamento> buscarTodos() {
        var retorno= repository.buscarTodos();
        return retorno;
    }

    @Override
    public boolean removerEstacionamento(Estacionamento estacionamento) {
        var retorno = repository.remover(estacionamento);
        return retorno;
    }

    @Override
    public boolean removerEstacionamento(int idEstacionamento) {
        var retorno = repository.remover(idEstacionamento);
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
