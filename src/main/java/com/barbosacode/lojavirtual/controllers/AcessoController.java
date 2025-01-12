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
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
        acesso = acessoService.salvar(acesso);
        return new ResponseEntity<>(acesso, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Acesso>> listarAcessos() {
        List<Acesso> acessos = acessoService.listarTodos();

        return new ResponseEntity<>(acessos, HttpStatus.OK);
    }


    @DeleteMapping("/all")
    public ResponseEntity<String> deletarTodosAcessos() {
        String responseMesage = acessoService.deletarTodos();
        return ResponseEntity.ok(responseMesage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acesso> buscarPorId(@PathVariable Long id) {
        Acesso acesso = acessoService.buscarPorId(id);
        return ResponseEntity.ok(acesso);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAcesso(@PathVariable Long id) {
        String returnMessage = acessoService.deletarPorId(id);
        return ResponseEntity.ok(returnMessage);
    }


    //    Retorno com custom
    @ResponseBody
    @PostMapping("**/salvar/custom")
    public ResponseEntity<CustomResponse<Acesso>> salvarAcessoCustom(@RequestBody Acesso acesso) {
        CustomResponse<Acesso> response = acessoService.salvarCustom(acesso);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/custom")
    public ResponseEntity<CustomResponse<Acesso>> buscarPorIdCustom(@PathVariable Long id) {
        CustomResponse<Acesso> acesso = acessoService.buscarPorIdCustom(id);
        return ResponseEntity.ok(acesso);
    }


    @DeleteMapping("/{id}/custom")
    public ResponseEntity<CustomResponse<Acesso>> deletarAcessoCustom(@PathVariable Long id) {
        CustomResponse<Acesso> acesso = acessoService.deletarPorIdCustom(id);
        return ResponseEntity.ok(acesso);
    }

    @DeleteMapping("/all/custom")
    public ResponseEntity<CustomResponse<Acesso>> deletarTodosAcessosCustom() {
        CustomResponse<Acesso> retorno = acessoService.deletarTodosCustom();
        return ResponseEntity.ok(retorno);
    }
}
