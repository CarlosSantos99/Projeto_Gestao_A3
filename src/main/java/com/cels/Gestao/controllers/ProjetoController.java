package com.cels.Gestao.controllers;

import com.cels.Gestao.entities.Projeto;
import com.cels.Gestao.entities.Usuario;
import com.cels.Gestao.services.ProjetoService;
import com.cels.Gestao.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {
    @Autowired
    private ProjetoService projetoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/teste")
    public String home() {
        return "API Gestao está no ar!";
    }

    // ✅ Criar projeto
    @PostMapping
    public ResponseEntity<Projeto> criar(@RequestBody Projeto projeto, @RequestParam Integer gerenteId) {
        // Buscar o usuário gerente
        Usuario gerente = usuarioService.buscar(gerenteId);

        // Associar gerente ao projeto e manter consistência
        projeto.setGerente(gerente);
        if (gerente.getProjetosGerenciados() != null) {
            gerente.getProjetosGerenciados().add(projeto);
        }

        Projeto novoProjeto = projetoService.salvar(projeto);
        return ResponseEntity.ok(novoProjeto);
    }

    // ✅ Listar todos os projetos
    @GetMapping
    public ResponseEntity<List<Projeto>> listar() {
        List<Projeto> projetos = projetoService.listarTodos();
        return ResponseEntity.ok(projetos);
    }

    // ✅ Buscar projeto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscar(@PathVariable Integer id) {
        Projeto projeto = projetoService.buscar(id);
        return ResponseEntity.ok(projeto);
    }

    // ✅ Atualizar projeto
    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Integer id, @RequestBody Projeto projeto, @RequestParam Integer gerenteId) {
        Usuario gerente = usuarioService.buscar(gerenteId);
        projeto.setGerente(gerente);

        Projeto atualizado = projetoService.atualizar(id, projeto);
        return ResponseEntity.ok(atualizado);
    }

    // ✅ Deletar projeto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}