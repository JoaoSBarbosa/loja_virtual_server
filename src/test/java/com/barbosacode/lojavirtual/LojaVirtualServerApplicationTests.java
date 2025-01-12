package com.barbosacode.lojavirtual;

import com.barbosacode.lojavirtual.controllers.AcessoController;
import com.barbosacode.lojavirtual.models.Acesso;
import com.barbosacode.lojavirtual.repositories.AcessoRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = LojaVirtualServerApplication.class)
public class LojaVirtualServerApplicationTests {

    @Autowired
    private AcessoController acessoController;
    @Autowired
    private AcessoRepository acessoRepository;
    @Autowired
    private WebApplicationContext context;


    private Acesso acesso;
    private Acesso acessoAdmin;
    private Acesso acessoUser;


    @Test
    public void testarCadastroAcessoAPI() throws JsonParseException, Exception {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_MOCK5");
        ObjectMapper jsonMapper = new ObjectMapper();
        ResultActions retornoApi = mockMvc
                .perform(MockMvcRequestBuilders.post("/acessos/salvar")
                        .content(jsonMapper.writeValueAsString(acesso))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                );

        // Imprimir a resposta da API
        String jsonResponse = retornoApi.andReturn().getResponse().getContentAsString();
        System.out.println("Retorno: " + jsonResponse);

        // Extração do campo "data"
        JsonNode rootNode = jsonMapper.readTree(jsonResponse);
        JsonNode dataNode = rootNode.get("data");

        // Mapear o campo "data" para a entidade Acesso
        Acesso entity = jsonMapper.treeToValue(dataNode, Acesso.class);
        System.out.println("Entidade: " + entity);
        // Validação
        Assertions.assertEquals("ROLE_MOCK5", entity.getDescricao());
    }


    public void testarDeletarAcessoAPI() throws JsonParseException, Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        MockMvc mockMvc = builder.build();
        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_MOCK5");

        acesso = acessoRepository.saveAndFlush(acesso);

        ObjectMapper jsonMapper = new ObjectMapper();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/acessos/{id}", acesso.getId()));

        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("Retorno: " + jsonResponse);


    }


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
