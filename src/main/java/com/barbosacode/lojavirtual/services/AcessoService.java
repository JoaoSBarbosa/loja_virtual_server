package com.barbosacode.lojavirtual.services;

import com.barbosacode.lojavirtual.models.Acesso;
import com.barbosacode.lojavirtual.repositories.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AcessoService {

    @Autowired
    AcessoRepository acessoRepository;

    @Transactional
    public Acesso salvar(Acesso acesso){
        return acessoRepository.save(acesso);
    }

    @Transactional(readOnly = true)
    public List<Acesso> listarTodos() {
        return acessoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Acesso buscarPorId(Long id) {
        return acessoRepository.findById(id).orElse(null);
    }
}
