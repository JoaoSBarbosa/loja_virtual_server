package com.barbosacode.lojavirtual;

import com.barbosacode.lojavirtual.controllers.AcessoController;
import com.barbosacode.lojavirtual.dto.AcessoDTO;
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

import java.util.List;

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
    public void deveCadastrarNovoAcessoViaAPI() throws JsonParseException, Exception {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_MOCK5");
        ObjectMapper jsonMapper = new ObjectMapper();
        ResultActions retornoApi = mockMvc
                .perform(MockMvcRequestBuilders.post("/acessos/salvar/custom")
                        .content(jsonMapper.writeValueAsString(acesso))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                );

        // Imprimir a resposta da API
        String jsonResponse = retornoApi.andReturn().getResponse().getContentAsString();
        System.out.println("Retorno - POST: " + jsonResponse);

        // Extração do campo "data"
        JsonNode rootNode = jsonMapper.readTree(jsonResponse);
        JsonNode dataNode = rootNode.get("data");

        // Mapear o campo "data" para a entidade Acesso
        Acesso entity = jsonMapper.treeToValue(dataNode, Acesso.class);
        System.out.println("Entidade: " + entity);
        // Validação
        Assertions.assertEquals("ROLE_MOCK5", entity.getDescricao());
    }


    @Test
    public void deveCadastrarAcessoEBuscarAcessoPorDescricaoViaAPI() throws JsonParseException, Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso("ROLE_MOCK_DESCRICAO");

        ObjectMapper jsonMapper = new ObjectMapper();

        // Envia uma requisição POST para salvar um Acesso
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/acessos/salvar")
                .content(jsonMapper.writeValueAsString(acesso))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        Acesso returnApi = jsonMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), Acesso.class);

        // Envia uma requisição GET para buscar o Acesso por descrição
        ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/acessos/description?desc=" + returnApi.getDescricao()));

        // Desserializa a resposta como uma lista de AcessoDTO
        List<AcessoDTO> acessos = jsonMapper.readValue(
                retornoApi.andReturn().getResponse().getContentAsString(),
                jsonMapper.getTypeFactory().constructCollectionType(List.class, AcessoDTO.class)
        );

        // Imprime os resultados
        acessos.forEach(ac -> {
            try {
                System.out.println("Retorno - GET DESCRIPTION: " + jsonMapper.writeValueAsString(ac));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Assertions.assertEquals(acesso.getDescricao(), acessos.get(0).getDescricao());
        acessoRepository.deleteById(returnApi.getId());
    }


//    @Test
//    public void deveCadastrarAcessoEBuscarAcessoPorDescricaoViaAPI() throws JsonParseException, Exception {
//        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
//        MockMvc mockMvc = builder.build();
//
//        Acesso acesso = new Acesso("ROLE_MOCK_DESCRICAO");
//
//        ObjectMapper jsonMapper = new ObjectMapper();
//
//        // Envia uma requisição POST para salvar um Acesso
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/acessos/salvar")
//                .content(jsonMapper.writeValueAsString(acesso))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        );
//        Acesso returnApi = jsonMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), Acesso.class);
//        // Envia uma requisição GET para buscar o Acesso por descrição
//        ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/acessos/description?desc=" + returnApi.getDescricao()));
//
//        // Desserializa a resposta como uma lista de Acesso
//        List<AcessoDTO> acessos = jsonMapper.readValue(retornoApi.andReturn()
//                .getResponse()
//                .getContentAsString(),
//                jsonMapper.getTypeFactory()
//                        .constructType(List.class, AcessoDTO.class));
//
//
//        // Imprime os resultados
//        acessos.forEach(ac -> {
//            try {
//                System.out.println("Retorno - GET DESCRIPTION: " + jsonMapper.writeValueAsString(ac));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    @Test
    public void deveExcluirAcessoViaAPI() throws JsonParseException, Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        MockMvc mockMvc = builder.build();
        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_MOCK5");
        ObjectMapper objectMapper = new ObjectMapper();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/acessos/salvar/custom")
                .content(objectMapper.writeValueAsString(acesso))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();

        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode dataNode = rootNode.get("data");
        Acesso entity = objectMapper.treeToValue(dataNode, Acesso.class);

        ResultActions resultActions2 = mockMvc.perform(MockMvcRequestBuilders.delete("/acessos/{id}", entity.getId()));

        String jsonResponse2 = resultActions2.andReturn().getResponse().getContentAsString();

        System.out.println("Retorno - DELETE: " + jsonResponse2);
        Assertions.assertEquals("Acesso deletado com sucesso!", jsonResponse2);
        Assertions.assertEquals(200, resultActions2.andReturn().getResponse().getStatus());

    }


    @Test
    public void deveCadastrarAcessoEBasicamenteValidarRetorno() throws Exception {
        // Configurar o mock para simular o contexto da aplicação (MockMvc)
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        MockMvc mockMvc = builder.build();

        // Instanciar o objeto que será enviado na requisição
        Acesso acesso = new Acesso("ROLE_TESTE_POST");

        // Serializar o objeto para JSON (necessário para enviar no corpo da requisição)
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonRequest = jsonMapper.writeValueAsString(acesso);

        // Fazer a requisição POST para a API com o objeto serializado
        ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/acessos/salvar")
                .content(jsonRequest) // JSON no corpo da requisição
                .contentType(MediaType.APPLICATION_JSON) // Tipo do conteúdo: JSON
                .accept(MediaType.APPLICATION_JSON)); // Aceitar resposta no formato JSON

        // Exibir o retorno no console (útil para debug)
        String jsonResponse = retornoApi.andReturn().getResponse().getContentAsString();
        System.out.println("Retorno - Basic POST: " + jsonResponse);

        // Validar o código de status da resposta (201 Created esperado)
        Assertions.assertEquals(201, retornoApi.andReturn().getResponse().getStatus());

        // Deserializar o JSON da resposta para um objeto Acesso
        Acesso acessoResposta = jsonMapper.readValue(jsonResponse, Acesso.class);

        // Validar se o objeto retornado é igual ao objeto enviado
        Assertions.assertEquals(acesso.getDescricao(), acessoResposta.getDescricao());
    }


    @Test
    public void deveCadastrarEConsultarAcessoPorIdViaAPI() throws Exception {
        // Configurar o mock para simular o contexto da aplicação (MockMvc)
        DefaultMockMvcBuilder build = MockMvcBuilders.webAppContextSetup(this.context);
        MockMvc mockMvc = build.build();

        // Instanciar o objeto que será enviado na requisição
        Acesso cadastro = new Acesso("ROLE_TESTE_GET");

        // Serializar o objeto para JSON (necessário para enviar no corpo da requisição)
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(cadastro);

        // Fazer a requisição POST para salvar o objeto na API
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/acessos/salvar/custom")
                .content(jsonRequest) // JSON no corpo da requisição
                .accept(MediaType.APPLICATION_JSON) // Aceitar resposta no formato JSON
                .contentType(MediaType.APPLICATION_JSON)); // Tipo do conteúdo: JSON

        // Obter o JSON da resposta
        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();

        // Ler o nó "data" da resposta para obter o objeto retornado
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode dataNode = rootNode.get("data");
        Acesso entity = objectMapper.treeToValue(dataNode, Acesso.class);

        // Fazer a requisição GET para buscar o objeto pelo ID retornado
        ResultActions resultActions1 = mockMvc.perform(MockMvcRequestBuilders.get("/acessos/{id}/custom", entity.getId())
                .accept(MediaType.APPLICATION_JSON));

        // Obter o JSON da resposta da requisição GET
        String jsonResponse1 = resultActions1.andReturn().getResponse().getContentAsString();

        // Ler o nó "data" da resposta para obter o objeto retornado
        JsonNode rootNode1 = objectMapper.readTree(jsonResponse1);
        JsonNode dataNode1 = rootNode1.get("data");
        Acesso entity2 = objectMapper.treeToValue(dataNode1, Acesso.class);

        // Exibir o objeto retornado no console (útil para debug)
        System.out.println("Entidade - Teste GET: " + entity2);

        // Validar se o objeto retornado possui a descrição correta
        Assertions.assertEquals("ROLE_TESTE_GET", entity2.getDescricao(), "A descrição do objeto retornado deve ser ROLE_TESTE_GET");

        // Validar se os objetos enviados e retornados possuem a mesma descrição
        Assertions.assertEquals(entity.getDescricao(), entity2.getDescricao(), "As descrições dos objetos devem ser iguais");
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
        Acesso salvo = acessoController.salvarAcessoCustom(acesso).getBody().getData();
        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("ROLE_TESTE", salvo.getDescricao());
    }

    @Test
    public void deveSalvarEntidadeComDescricaoADMIN() {
        Acesso salvo = acessoController.salvarAcessoCustom(acessoAdmin).getBody().getData();
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
