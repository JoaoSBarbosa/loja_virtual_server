![Último Commit](https://img.shields.io/github/last-commit/JoaoSBarbosa/loja_virtual_server?style=flat-square&label=Último%20Commit&color=blue)
![Versão](https://img.shields.io/badge/vers%C3%A3o-1.0.0-blue?style=flat-square)
![Java](https://img.shields.io/badge/Java-11-blue?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-green?style=flat-square&logo=spring)
![Angular](https://img.shields.io/badge/Angular-15-red?style=flat-square&logo=angular)
![Contribuições](https://img.shields.io/badge/Contribui%C3%A7%C3%B5es-Bem%20Vindas-brightgreen?style=flat-square)
![Tamanho do Código](https://img.shields.io/github/languages/code-size/JoaoSBarbosa/loja_virtual_server?style=flat-square&label=Tamanho%20do%20C%C3%B3digo)
![GitHub top language](https://img.shields.io/github/languages/top/JoaoSBarbosa/loja_virtual_server)
![Linguagens Utilizadas](https://img.shields.io/github/languages/count/JoaoSBarbosa/loja_virtual_server?style=flat-square&label=Linguagens&logo=github&color=purple&logoColor=white)
![Observadores](https://img.shields.io/github/watchers/JoaoSBarbosa/loja_virtual_server?style=flat-square&label=Observadores&logo=eye&color=brightgreen)
![Forks](https://img.shields.io/github/forks/JoaoSBarbosa/loja_virtual_server?style=flat-square&label=Forks&logo=repo-forked&color=blue)
![Estrelas](https://img.shields.io/github/stars/JoaoSBarbosa/loja_virtual_server?style=flat-square&label=Estrelas&logo=star&color=gold)
![Acessos](https://img.shields.io/badge/Acessos-1234-blue?style=flat-square&logo=google-analytics&color=teal)

# E-commerce System

![Diagrama de Classes](https://raw.githubusercontent.com/JoaoSBarbosa/loja_virtual_server/refs/heads/main/src/main/resources/static/img/Sistema%20E-commerce%20Diagrama%20Classe.jpg)
## Sobre o Projeto

Este projeto é um sistema completo de e-commerce desenvolvido utilizando **Java Spring**. O sistema inclui funcionalidades para gerenciar usuários, produtos, vendas, pagamentos, avaliações, estoque e muito mais. Ele foi projetado com base em boas práticas de desenvolvimento e uma arquitetura robusta para garantir escalabilidade e manutenção.

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-11-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.7.5-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-12-blue?style=for-the-badge&logo=postgresql)
![Hibernate](https://img.shields.io/badge/Hibernate-5.6.7-purple?style=for-the-badge&logo=hibernate)
![Maven](https://img.shields.io/badge/Maven-3.8.5-C71A36?style=for-the-badge&logo=apachemaven)

## Funcionalidades Principais

- **Usuários e Acessos**: Controle de usuários e seus níveis de acesso.
- **Cadastro de Pessoas**: Gerenciamento de pessoas físicas e jurídicas.
- **Gestão de Produtos**: Cadastro de produtos, imagens, categorias e marcas.
- **Controle de Estoque**: Monitoramento de estoque com alertas.
- **Processos de Venda e Compra**: Registro e acompanhamento de vendas e compras.
- **Gestão Financeira**: Controle de contas a pagar e receber.
- **Rastreamento de Pedidos**: Gerenciamento de status de entrega e rastreamento.
- **Avaliação de Produtos**: Sistema de avaliação e notas dos produtos.

## Arquitetura do Sistema

O sistema é composto pelas seguintes entidades principais:

- **Usuário e Acessos**:
  - `Usuario`
  - `UsuarioAcesso`
  - `Acesso`
- **Cadastro de Pessoas**:
  - `Pessoa`
  - `PessoaFisica`
  - `PessoaJuridica`
  - `Telefone`
  - `Endereco`
- **Gestão de Produtos**:
  - `Produto`
  - `CategoriaProduto`
  - `MarcaProduto`
  - `ImagemProduto`
- **Processos de Compra e Venda**:
  - `VendaCompraLoja`
  - `ItemVenda`
  - `NotaFiscalVenda`
  - `NotaFiscalCompra`
  - `NotaItemProd`
- **Financeiro**:
  - `ContaPagar`
  - `ContaReceber`
  - `FormaPagamento`
  - `Cupom`
- **Rastreamento e Avaliação**:
  - `StatusRastreio`
  - `Avaliacao`

Para detalhes completos sobre o diagrama de classes, veja a imagem acima.

## SQL

Devido à estrutura da entidade abstrata `Pessoa`, triggers foram configurados para garantir a integridade referencial ao associar pessoas físicas ou jurídicas.

**Exemplo de Trigger**:

```postgresql
CREATE OR REPLACE FUNCTION validachavepessoa()
	returns trigger
	language plpgsql
	as $$
	declare existe integer;
	begin
		existe = (SELECT COUNT(1) FROM pessoa_fisica WHERE id = NEW.id_pessoa);
		if(existe <= 0) then
		existe = (SELECT COUNT(1) FROM pessoa_juridica WHERE id = NEW.id_pessoa);
		if(existe <= 0) then
            RAISE EXCEPTION 'Não foi encontrado o ID e PK da pessoa para realizar a associação do cadastro';
		end if;
		end if;
	RETURN NEW;
	end;
	$$
```

```postgresql
CREATE OR REPLACE TRIGGER validachavepessoaavaliacao
    BEFORE INSERT OR UPDATE 
    ON public.avaliacao
    FOR EACH ROW
    EXECUTE FUNCTION public.validachavepessoa();
```



### Consultas e Joins

```postgresql
SELECT 
	p.id AS "Código",
	p.descricao_curta AS "Descrição",
	p.nome AS "Produto",
	m.descricao AS "Marca",
	c.descricao AS "Categoria"
FROM produto p
INNER JOIN marca_produto m ON p.id_marca_produto = m.id
INNER JOIN categoria_produto c ON p.id_categoria_produto = c.id;
```

```postgresql
1	"Smartphone com 128GB"	"Smartphone XPro"	"Marca XTech"	"Smartphones e Acessórios"
2	"Notebook i7 com SSD"	"Notebook Turbo 15"	"TurboTech"	"Notebooks e Computadores"
3	"Fone de Ouvido Bluetooth"	"Fone Premium"	"PremiumSound"	"Fones de Ouvido"
4	"Monitor 4K UHD"	"Monitor Ultra 27"""	"UltraVision"	"Monitores e Displays"
5	"Teclado Mecânico RGB"	"Teclado Gamer Pro"	"GamerPro"	"Teclados e Periféricos"
6	"Tablet com Caneta"	"Tablet Max 10"	"MaxTech"	"Tablets e Dispositivos Móveis"
7	"Smartwatch com GPS"	"Smartwatch Fit"	"FitWear"	"Smartwatches e Wearables"
8	"Carregador Turbo USB-C"	"Carregador Turbo"	"ChargeMaster"	"Carregadores e Cabos"
9	"Câmera de Ação 4K"	"Action Cam Go"	"ActionVision"	"Câmeras e Equipamentos de Vídeo"
10	"Cabo HDMI 2.1"	"Cabo HDMI Premium"	"CablePro"	"Cabos e Conectores"
```

| Código (pk) | Descrição                | Produto           | Marca        | Categoria                |
| ----------- | ------------------------ | ----------------- | ------------ | ------------------------ |
| 1           | Smartphone com 128GB     | Smartphone XPro   | Marca XTech  | Smartphones e Acessórios |
| 2           | Notebook i7 com SSD      | Notebook Turbo 15 | TurboTech    | Notebooks e Computadores |
| 3           | Fone de Ouvido Bluetooth | Fone Premium      | PremiumSound | Fones de Ouvido          |



## Pré-requisitos

- **Java 11 ou superior**
- **Maven**
- **PostgreSQL 12**
- IDE de sua escolha (ex.: IntelliJ IDEA, Eclipse, VS Code)

## Configuração do Projeto

1. Clone este repositório:

   ```bash
   git clone https://github.com/seu-usuario/ecommerce-system.git
   ```

2. Configure o banco de dados PostgreSQL no arquivo `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Instale as dependências com Maven:

   ```bash
   mvn clean install
   ```

4. Execute o projeto:

   ```bash
   mvn spring-boot:run
   ```

## Endpoints Disponíveis

| Método | Endpoint      | Descrição                               |
| ------ | ------------- | --------------------------------------- |
| GET    | `/usuarios`   | Lista todos os usuários.                |
| POST   | `/usuarios`   | Cadastra um novo usuário.               |
| GET    | `/produtos`   | Lista todos os produtos.                |
| POST   | `/produtos`   | Cadastra um novo produto.               |
| GET    | `/vendas`     | Lista todas as vendas realizadas.       |
| POST   | `/vendas`     | Registra uma nova venda.                |
| GET    | `/avaliacoes` | Lista todas as avaliações de produtos.  |
| POST   | `/avaliacoes` | Cadastra uma avaliação para um produto. |

## Testes

1. Utilize o **Postman** ou **Insomnia** para testar os endpoints disponíveis.
2. Testes automatizados podem ser executados com o comando:

   ```bash
   mvn test
   ```

## Contribuição

Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

1. Faça um fork do projeto.
2. Crie uma branch para sua feature:

   ```bash
   git checkout -b feature/nome-da-sua-feature
   ```

3. Commit suas alterações:

   ```bash
   git commit -m ":sparkles: feat: descrição da sua feature"
   ```

4. Envie sua branch para o repositório remoto:

   ```bash
   git push origin feature/nome-da-sua-feature
   ```

5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo [LICENSE](./LICENSE) para mais informações.

## Autor

Desenvolvido por [João Barbosa by BarbosaCode](https://joaobarbosadev.vercel.app/).
