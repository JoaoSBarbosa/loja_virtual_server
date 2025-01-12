package com.barbosacode.lojavirtual.services;

import com.barbosacode.lojavirtual.dto.CustomResponse;
import com.barbosacode.lojavirtual.exceptions.ControllerNotFoundException;
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

    @Autowired
    HttpServletRequest httpServletRequest;

    @Transactional
    public Acesso salvar(Acesso acesso) {
        return acessoRepository.save(acesso);
    }

    @Transactional(readOnly = true)
    public List<Acesso> listarTodos() {
        return acessoRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Acesso buscarPorId(Long id) {
        return acessoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Não foi localizado registro de Acessos com o id informado"));
    }

    @Transactional
    public String deletarPorId(Long id) {
        Acesso acesso = acessoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Erro ao deletar o acesso"));
        acessoRepository.deleteById(acesso.getId());
        return "Acesso deletado com sucesso!";
    }

    @Transactional
    public String deletarTodos() {
        try {
            acessoRepository.deleteAll();
            return "Acesso removido com sucesso!";
        } catch (Exception e) {
            throw new ControllerNotFoundException("Erro ao deletar o acesso");
        }
    }


    //    custom
    @Transactional
    public CustomResponse<Acesso> salvarCustom(Acesso acesso) {

        acesso = acessoRepository.save(acesso);

        CustomResponse<Acesso> response = new CustomResponse<>();
        response.setMessage("Acesso salvo com sucesso!");
        response.setPath(httpServletRequest.getRequestURI());
        response.setTimestamp(Util.getLocalTime());
        response.setStatus(HttpStatus.CREATED.value());
        response.setData(acesso);
        return response;
    }

    @Transactional(readOnly = true)
    public CustomResponse<Acesso> buscarPorIdCustom(Long id) {

        Acesso acesso = acessoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Não foi localizado registro de Acessos com o id informado"));

        CustomResponse<Acesso> response = new CustomResponse<>();
        response.setMessage("Acesso localizado com sucesso!");
        response.setPath(httpServletRequest.getRequestURI());
        response.setTimestamp(Util.getLocalTime());
        response.setStatus(HttpStatus.FOUND.value());
        response.setData(acesso);
        return response;
    }

    @Transactional
    public CustomResponse<Acesso> deletarPorIdCustom(Long id) {
        Acesso acesso = acessoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Erro ao deletar o acesso"));
        acessoRepository.deleteById(acesso.getId());
        CustomResponse<Acesso> response = new CustomResponse<>();
        response.setMessage("Acesso deletado com sucesso!");
        response.setPath(httpServletRequest.getRequestURI());
        response.setTimestamp(Util.getLocalTime());
        response.setStatus(HttpStatus.OK.value());
        response.setData(null);
        return response;
    }

    @Transactional
    public CustomResponse<Acesso> deletarTodosCustom() {
        try {
            acessoRepository.deleteAll();
            CustomResponse<Acesso> response = new CustomResponse<>();
            response.setMessage("AAcesso removido com sucesso!");
            response.setPath(httpServletRequest.getRequestURI());
            response.setTimestamp(Util.getLocalTime());
            response.setStatus(HttpStatus.OK.value());
            response.setData(null);
            return response;
        } catch (Exception e) {
            throw new ControllerNotFoundException("Erro ao deletar o acesso");
        }
    }
}
