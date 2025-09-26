package com.cels.Gestao.controllers;

import com.cels.Gestao.entities.Equipe;
import com.cels.Gestao.entities.Projeto;
import com.cels.Gestao.services.EquipeService;
import com.cels.Gestao.services.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipes")
@RequiredArgsConstructor
public class EquipeController {
    @Autowired
    private EquipeService equipeService;

    @GetMapping("/teste")
    public String home() {
        return "API Gestao está no ar!";
    }

    // ✅ Criar usuário
    @PostMapping
    public ResponseEntity<Equipe> criar(@RequestBody Equipe equipe) {
        Equipe novoEquipe = equipeService.salvar(equipe);
        return ResponseEntity.ok(novoEquipe);
    }

    // ✅ Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Equipe>> listar() {
        List<Equipe> equipes = equipeService.listarTodos();
        return ResponseEntity.ok(equipes);
    }

    // ✅ Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipe> buscar(@PathVariable Integer id) {
        Equipe equipe = equipeService.buscar(id);
        return ResponseEntity.ok(equipe);
    }

    // ✅ Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Equipe> atualizar(@PathVariable Integer id, @RequestBody Equipe equipe) {
        Equipe atualizado = equipeService.atualizar(id, equipe);
        return ResponseEntity.ok(atualizado);
    }

    // ✅ Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        equipeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}