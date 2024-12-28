package com.barbosacode.lojavirtual.controllers;

import com.barbosacode.lojavirtual.models.Acesso;
import com.barbosacode.lojavirtual.services.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessos")
public class AcessoController {

    @Autowired
    AcessoService acessoService;

    @PostMapping
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
        acesso = acessoService.salvar(acesso);
        return ResponseEntity.ok(acesso);
    }

    @GetMapping
    public ResponseEntity<List<Acesso>> listarAcessos() {
        List<Acesso> acessos = acessoService.listarTodos();
        return ResponseEntity.ok(acessos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acesso> buscarPorId(@PathVariable Long id) {
        Acesso acesso = acessoService.buscarPorId(id);
        return ResponseEntity.ok(acesso);
    }
}
