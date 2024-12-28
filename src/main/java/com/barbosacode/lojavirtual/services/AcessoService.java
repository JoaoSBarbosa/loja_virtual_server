package com.barbosacode.lojavirtual.services;

import com.barbosacode.lojavirtual.dto.CustomResponse;
import com.barbosacode.lojavirtual.models.Acesso;
import com.barbosacode.lojavirtual.repositories.AcessoRepository;
import com.barbosacode.lojavirtual.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;

@Service
public class AcessoService {

    @Autowired
    AcessoRepository acessoRepository;

    @Autowired HttpServletRequest httpServletRequest;

    @Transactional
    public CustomResponse<Acesso> salvar(Acesso acesso) {

        acesso = acessoRepository.save(acesso);

        CustomResponse<Acesso> response = new CustomResponse<>();
        response.setMessage("Acesso salvo com sucesso!");
        response.setPath(httpServletRequest.getRequestURI());
        response.setTimestamp(Util.getLocalTime());
        response.setStatus(HttpStatus.CREATED.value());
        response.setData(acesso);
        return  response;
    }

    @Transactional(readOnly = true)
    public List<Acesso> listarTodos() {
        return acessoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CustomResponse<Acesso> buscarPorId(Long id) {

        Acesso acesso = acessoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi localizado registro de Acessos com o id informado"));

        CustomResponse<Acesso> response = new CustomResponse<>();
        response.setMessage("Acesso localizado com sucesso!");
        response.setPath(httpServletRequest.getRequestURI());
        response.setTimestamp(Util.getLocalTime());
        response.setStatus(HttpStatus.FOUND.value());
        response.setData(acesso);
        return  response;
    }

    @Transactional
    public  CustomResponse<Acesso>  deletarPorId(Long id) {
        Acesso acesso = acessoRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro ao deletar o acesso"));
        acessoRepository.deleteById(acesso.getId());
        CustomResponse<Acesso> response = new CustomResponse<>();
        response.setMessage("Acesso delato com sucesso!");
        response.setPath(httpServletRequest.getRequestURI());
        response.setTimestamp(Util.getLocalTime());
        response.setStatus(HttpStatus.OK.value());
        response.setData(null);
        return  response;
    }

    @Transactional
    public CustomResponse<Acesso>  deletarTodos() {
        try {
            acessoRepository.deleteAll();
            CustomResponse<Acesso> response = new CustomResponse<>();
            response.setMessage("AAcesso removido com sucesso!");
            response.setPath(httpServletRequest.getRequestURI());
            response.setTimestamp(Util.getLocalTime());
            response.setStatus(HttpStatus.OK.value());
            response.setData(null);
            return  response;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o acesso", e);
        }
    }
}
