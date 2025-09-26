package com.cels.Gestao.controllers;

import com.cels.Gestao.entities.Projeto;
import com.cels.Gestao.entities.Usuario;
import com.cels.Gestao.services.ProjetoService;
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


    @GetMapping("/teste")
    public String home() {
        return "API Gestao está no ar!";
    }

    // ✅ Criar usuário
    @PostMapping
    public ResponseEntity<Projeto> criar(@RequestBody Projeto projeto) {
        Projeto novoProjeto = projetoService.salvar(projeto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ResponseEntity.ok(novoProjeto);
    }

    // ✅ Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Projeto>> listar() {
        List<Projeto> projetos = projetoService.listarTodos();
        return ResponseEntity.ok(projetos);
    }

    // ✅ Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscar(@PathVariable Integer id) {
        Projeto projeto = projetoService.buscar(id);
        return ResponseEntity.ok(projeto);
    }

    // ✅ Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Integer id, @RequestBody Projeto projeto) {
        Projeto atualizado = projetoService.atualizar(id, projeto);
        return ResponseEntity.ok(atualizado);
    }

    // ✅ Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}