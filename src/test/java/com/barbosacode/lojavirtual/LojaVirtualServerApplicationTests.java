package com.barbosacode.lojavirtual;

import com.barbosacode.lojavirtual.controllers.AcessoController;
import com.barbosacode.lojavirtual.dto.CustomResponse;
import com.barbosacode.lojavirtual.models.Acesso;
import com.barbosacode.lojavirtual.repositories.AcessoRepository;
import com.barbosacode.lojavirtual.services.AcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(classes = LojaVirtualServerApplication.class)
class LojaVirtualServerApplicationTests {

	@Autowired
	AcessoController acessoController;


	@Test
	public void salvarAcesso() {
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_TECH");
//		acesso = acessoService.salvar(acesso);
		ResponseEntity<CustomResponse<Acesso>> acesso1 = acessoController.salvarAcesso(acesso);
		System.out.println("Acesso salvo com sucesso: " + ResponseEntity.status(acesso1.getStatusCode()).body(acesso1));
	}


	@Test
	public void obterAcesso() {
		ResponseEntity<List<Acesso>> acessos = acessoController.listarAcessos();
		System.out.println("Acesso salvo com sucesso: " + ResponseEntity.status(acessos.getStatusCode()).body(acessos));
	}



}
