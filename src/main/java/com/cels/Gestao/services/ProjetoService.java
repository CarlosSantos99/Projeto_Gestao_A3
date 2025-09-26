package com.cels.Gestao.services;

import com.cels.Gestao.entities.Projeto;
import com.cels.Gestao.repositories.ProjetoRepository;
import com.cels.Gestao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository repo;

    public Projeto salvar(Projeto projeto) {
        return repo.save(projeto);
    }

    public List<Projeto> listarTodos() {
        return repo.findAll();
    }

    public Projeto buscar(Integer id) {
        Optional<Projeto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Projeto.class.getName()));
    }

    public Projeto atualizar(Integer id, Projeto projetoAtualizado) {
        Projeto projetoExistente = buscar(id);
        projetoExistente.setNome(projetoAtualizado.getNome());
        projetoExistente.setDescricao(projetoAtualizado.getDescricao());
        projetoExistente.setDataInicio(projetoAtualizado.getDataInicio());
        projetoExistente.setDataFimPrevista(projetoAtualizado.getDataFimPrevista());
        return repo.save(projetoExistente);
    }

    public void deletar(Integer id) {
        Projeto projetoExistente = buscar(id);
        repo.delete(projetoExistente);
    }
}
