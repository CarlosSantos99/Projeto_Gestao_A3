package com.cels.Gestao.services;

import com.cels.Gestao.entities.Equipe;
import com.cels.Gestao.repositories.EquipeRepository;
import com.cels.Gestao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository repo;

    public Equipe salvar(Equipe equipe) {
        return repo.save(equipe);
    }

    public List<Equipe> listarTodos() {
        return repo.findAll();
    }

    public Equipe buscar(Integer id) {
        Optional<Equipe> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Equipe.class.getName()));
    }

    public Equipe atualizar(Integer id, Equipe equipeAtualizado) {
        Equipe equipeExistente = buscar(id);
        equipeExistente.setNome(equipeAtualizado.getNome());
        equipeExistente.setDescricao(equipeAtualizado.getDescricao());

        return repo.save(equipeExistente);
    }

    public void deletar(Integer id) {
        Equipe equipeExistente = buscar(id);
        repo.delete(equipeExistente);
    }
}
