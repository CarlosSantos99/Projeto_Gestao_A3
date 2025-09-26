package com.cels.Gestao.services;

import com.cels.Gestao.entities.Projeto;
import com.cels.Gestao.entities.Usuario;
import com.cels.Gestao.repositories.ProjetoRepository;
import com.cels.Gestao.repositories.UsuarioRepository;
import com.cels.Gestao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepository; // precisa injetar também

    public Projeto salvar(Projeto projeto, Integer usuarioId) {
        // Buscar usuário
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Associar o projeto ao gerente
        projeto.setGerente(usuario);

        // Adicionar o projeto à lista de projetos do usuário (bidirecional)
        if (usuario.getProjetosGerenciados() != null) {
            usuario.getProjetosGerenciados().add(projeto);
        }

        // Salvar projeto
        return repo.save(projeto);
    }



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
