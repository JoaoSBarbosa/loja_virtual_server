package com.barbosacode.lojavirtual.controllers;

import com.barbosacode.lojavirtual.services.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acessos")
public class AcessoController {

    @Autowired
    AcessoService acessoService;
}
