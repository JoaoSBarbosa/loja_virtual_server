package com.barbosacode.lojavirtual.controllers;

import com.barbosacode.lojavirtual.dto.CustomResponse;
import com.barbosacode.lojavirtual.models.Acesso;
import com.barbosacode.lojavirtual.services.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessos")
public class AcessoController {

    @Autowired
    AcessoService acessoService;

    @ResponseBody
    @PostMapping("**/salvar")
    public ResponseEntity<CustomResponse<Acesso>> salvarAcesso(@RequestBody Acesso acesso) {
        CustomResponse<Acesso> response = acessoService.salvar(acesso);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Acesso>> listarAcessos() {
        List<Acesso> acessos = acessoService.listarTodos();

        return new ResponseEntity<>(acessos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Acesso>> buscarPorId(@PathVariable Long id) {
        CustomResponse<Acesso> acesso = acessoService.buscarPorId(id);
        return ResponseEntity.ok(acesso);
    }

    @DeleteMapping("/{id}/custom")
    public ResponseEntity<CustomResponse<Acesso>> deletarAcessoCustom(@PathVariable Long id) {
        CustomResponse<Acesso> acesso = acessoService.deletarPorIdCustom(id);
        return ResponseEntity.ok(acesso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAcesso(@PathVariable Long id) {
        String returnMessage = acessoService.deletarPorId(id);
        return ResponseEntity.ok(returnMessage);
    }

    @DeleteMapping("/all")
    public ResponseEntity<CustomResponse<Acesso>> deletarAcessos() {
        CustomResponse<Acesso> retorno = acessoService.deletarTodos();
        return ResponseEntity.ok(retorno);
    }
}
