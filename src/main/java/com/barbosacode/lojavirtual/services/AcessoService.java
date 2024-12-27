package com.barbosacode.lojavirtual.services;

import com.barbosacode.lojavirtual.repositories.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {

    @Autowired
    AcessoRepository acessoRepository;
}
