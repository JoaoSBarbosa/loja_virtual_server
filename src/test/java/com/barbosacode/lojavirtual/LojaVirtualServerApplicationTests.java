package com.barbosacode.lojavirtual;

import com.barbosacode.lojavirtual.controllers.AcessoController;
import com.barbosacode.lojavirtual.models.Acesso;
import com.barbosacode.lojavirtual.repositories.AcessoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = LojaVirtualServerApplication.class)
public class LojaVirtualServerApplicationTests {

	@Autowired
	private AcessoController acessoController;

	@Autowired
	private AcessoRepository acessoRepository;

	private Acesso acesso;
	private Acesso acessoAdmin;
	private Acesso acessoUser;

	@BeforeEach
	void setUp() {
		acesso = new Acesso();
		acesso.setDescricao("ROLE_TESTE");

		acessoAdmin = new Acesso();
		acessoAdmin.setDescricao("ROLE_ADMIN");

		acessoUser = new Acesso();
		acessoUser.setDescricao("ROLE_USER");
	}

	@Test
	public void deveSalvarEntidadeQuandoDescricaoExistente() {
		Acesso salvo = acessoController.salvarAcesso(acesso).getBody().getData();
		assertNotNull(salvo);
		assertNotNull(salvo.getId());
		assertEquals("ROLE_TESTE", salvo.getDescricao());
	}

	@Test
	public void deveSalvarEntidadeComDescricaoADMIN() {
		Acesso salvo = acessoController.salvarAcesso(acessoAdmin).getBody().getData();
		assertNotNull(salvo);
		assertNotNull(salvo.getId());
		assertEquals("ROLE_ADMIN", salvo.getDescricao());
	}

	@Test
	public void deveBuscarEExcluirEntidadePorId() {
		// Salvar acessoUser no banco
		Acesso salvo = acessoRepository.save(acessoUser);
		assertNotNull(salvo.getId());

		// Buscar acessoUser pelo ID
		Acesso encontrado = acessoRepository.findById(salvo.getId()).orElse(null);
		assertNotNull(encontrado);
		assertEquals(salvo.getId(), encontrado.getId());

		// Excluir acessoUser
		acessoRepository.deleteById(salvo.getId());
		acessoRepository.flush();

		// Verificar se foi excluído
		Acesso excluido = acessoRepository.findById(salvo.getId()).orElse(null);
		Assertions.assertNull(excluido);
	}


}
