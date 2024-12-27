package com.barbosacode.lojavirtual;

import com.barbosacode.lojavirtual.models.Acesso;
import com.barbosacode.lojavirtual.repositories.AcessoRepository;
import com.barbosacode.lojavirtual.services.AcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LojaVirtualServerApplication.class)
class LojaVirtualServerApplicationTests {

	@Autowired
	AcessoService acessoService;
	@Autowired
	AcessoRepository acessoRepository;



	@Test
	public void cadastraAcesso() {

		Acesso acesso = new Acesso();
		acesso.setDescricao("ADMIN");
		acessoRepository.save(acesso);
	}
	@Test
	public void cadastraAcesso2() {
		Acesso acesso = new Acesso();
		acesso.setDescricao("USER");
		acessoRepository.save(acesso);
	}

	@Test
	public void obterAcesso() {
		acessoRepository.findAll().forEach(System.out::println);
	}

	@Test
	void contextLoads() {
	}

}
