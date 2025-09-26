package com.cels.Gestao.services;

import com.cels.Gestao.entities.Usuario;
import com.cels.Gestao.repositories.UsuarioRepository;
import com.cels.Gestao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Usuario salvar(Usuario usuario) {
        return repo.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return repo.findAll();
    }

    public Usuario buscar(Integer id) {
        Optional<Usuario> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario atualizar(Integer id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscar(id);
        usuarioExistente.setNomeCompleto(usuarioAtualizado.getNomeCompleto());
        usuarioExistente.setCpf(usuarioAtualizado.getCpf());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setCargo(usuarioAtualizado.getCargo());
        usuarioExistente.setLogin(usuarioAtualizado.getLogin());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setPerfil(usuarioAtualizado.getPerfil());

        return repo.save(usuarioExistente);
    }

    public void deletar(Integer id) {
        Usuario usuarioExistente = buscar(id);
        repo.delete(usuarioExistente);
    }
}



