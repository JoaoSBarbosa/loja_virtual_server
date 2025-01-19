

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


CREATE DATABASE db_loja_virtual WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'pt_BR.UTF-8';


ALTER DATABASE db_loja_virtual OWNER TO postgres;


SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


ALTER SCHEMA public OWNER TO pg_database_owner;


COMMENT ON SCHEMA public IS 'standard public schema';


CREATE FUNCTION public.registrar_log_sistema() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO log_sistema (data_registro_log, descricao_log, hora_registro_log, id_usuario)
        VALUES (CURRENT_DATE, 'Exclusão de registro da tabela ' || TG_TABLE_NAME || ' com ID ' || OLD.id, CURRENT_TIME, OLD.id_usuario);
RETURN OLD;
END IF;

    IF (TG_OP = 'UPDATE') THEN
        INSERT INTO Log_sistema (data_registro_log, descricao_log, hora_registro_log, id_usuario)
        VALUES (CURRENT_DATE, 'Atualização de registro na tabela ' || TG_TABLE_NAME || ' com ID ' || NEW.id, CURRENT_TIME, NEW.id_usuario);
RETURN NEW;
END IF;

RETURN NULL;
END;
$$;


ALTER FUNCTION public.registrar_log_sistema() OWNER TO postgres;


CREATE FUNCTION public.validachavepessoa() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
existe INTEGER;
BEGIN
    existe = (SELECT COUNT(1) FROM pessoa_fisica WHERE id = NEW.id_pessoa);
    IF existe <= 0 THEN
        existe = (SELECT COUNT(1) FROM pessoa_juridica WHERE id = NEW.id_pessoa);
        IF existe <= 0 THEN
            RAISE EXCEPTION 'Não foi encontrado o ID e PK da pessoa para realizar a associação do cadastro';
END IF;
END IF;

RETURN NEW;
END;
$$;


ALTER FUNCTION public.validachavepessoa() OWNER TO postgres;


CREATE FUNCTION public.validachavepessoaforn() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare existe integer;
begin
		existe = (SELECT COUNT(1) FROM pessoa_fisica WHERE id = NEW.id_fornecedor);
		if(existe <= 0) then
		existe = (SELECT COUNT(1) FROM pessoa_juridica WHERE id = NEW.id_fornecedor);
		if(existe <= 0) then
            RAISE EXCEPTION 'Não foi encontrado o ID e PK da pessoa para realizar a associação do cadastro';
end if;
end if;
RETURN NEW;
end;
	$$;


ALTER FUNCTION public.validachavepessoaforn() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE public.acesso (
                               id bigint NOT NULL,
                               descricao character varying(255) NOT NULL
);


ALTER TABLE public.acesso OWNER TO postgres;


CREATE TABLE public.avaliacao (
                                  id bigint NOT NULL,
                                  data_avaliacao date,
                                  descricao character varying(255) NOT NULL,
                                  nota integer NOT NULL,
                                  id_pessoa bigint NOT NULL,
                                  id_produto bigint NOT NULL
);


ALTER TABLE public.avaliacao OWNER TO postgres;


CREATE TABLE public.categoria_produto (
                                          id bigint NOT NULL,
                                          descricao character varying(255) NOT NULL
);


ALTER TABLE public.categoria_produto OWNER TO postgres;

CREATE TABLE public.conta_pagar (
                                    id bigint NOT NULL,
                                    data_pagamento date,
                                    data_vencimento date NOT NULL,
                                    descricao character varying(255) NOT NULL,
                                    status character varying(255) NOT NULL,
                                    valor_desconto numeric(19,2),
                                    valor_pago numeric(19,2),
                                    valor_total numeric(19,2) NOT NULL,
                                    id_forma_pagamento bigint NOT NULL,
                                    id_fornecedor bigint NOT NULL,
                                    id_pessoa bigint NOT NULL
);


ALTER TABLE public.conta_pagar OWNER TO postgres;


CREATE TABLE public.conta_receber (
                                      id bigint NOT NULL,
                                      data_pagamento date,
                                      data_vencimento date NOT NULL,
                                      descricao character varying(255) NOT NULL,
                                      status character varying(255) NOT NULL,
                                      valor_desconto numeric(19,2),
                                      valor_total numeric(19,2) NOT NULL,
                                      id_pessoa bigint NOT NULL
);


ALTER TABLE public.conta_receber OWNER TO postgres;

CREATE TABLE public.cupom_desconto (
                                       id bigint NOT NULL,
                                       codigo_desconto character varying(255) NOT NULL,
                                       data_limite date NOT NULL,
                                       valor_percentual_desconto numeric(19,2),
                                       valor_real_desconto numeric(19,2)
);


ALTER TABLE public.cupom_desconto OWNER TO postgres;



CREATE TABLE public.endereco (
                                 id bigint NOT NULL,
                                 bairro character varying(255) NOT NULL,
                                 cep character varying(255) NOT NULL,
                                 cidade character varying(255) NOT NULL,
                                 complemento character varying(255),
                                 longradouro character varying(255) NOT NULL,
                                 numero character varying(255) NOT NULL,
                                 tipo_endereco character varying(255) NOT NULL,
                                 uf character varying(255) NOT NULL,
                                 id_pessoa bigint NOT NULL
);


ALTER TABLE public.endereco OWNER TO postgres;



CREATE TABLE public.forma_pagamento (
                                        id bigint NOT NULL,
                                        descricao character varying(255) NOT NULL
);


ALTER TABLE public.forma_pagamento OWNER TO postgres;


CREATE TABLE public.imagem_produto (
                                       id bigint NOT NULL,
                                       imagem_miniatura text NOT NULL,
                                       imagem_original text NOT NULL,
                                       id_produto bigint NOT NULL
);


ALTER TABLE public.imagem_produto OWNER TO postgres;



CREATE TABLE public.item_venda (
                                   id bigint NOT NULL,
                                   quantidade double precision NOT NULL,
                                   id_produto bigint NOT NULL,
                                   id_venda_compra bigint NOT NULL
);


ALTER TABLE public.item_venda OWNER TO postgres;



CREATE TABLE public.log_sistema (
                                    id bigint NOT NULL,
                                    data_registro_log date NOT NULL,
                                    descricao_log character varying(255) NOT NULL,
                                    hora_registro_log time without time zone,
                                    id_usuario bigint
);


ALTER TABLE public.log_sistema OWNER TO postgres;



CREATE TABLE public.marca_produto (
                                      id bigint NOT NULL,
                                      descricao character varying(255) NOT NULL
);


ALTER TABLE public.marca_produto OWNER TO postgres;


CREATE TABLE public.nota_fiscal_compra (
                                           id bigint NOT NULL,
                                           data_compra date NOT NULL,
                                           descricao character varying(255),
                                           numero_nota character varying(255),
                                           serie character varying(255) NOT NULL,
                                           valor_desconto numeric(19,2),
                                           valor_icms numeric(19,2) NOT NULL,
                                           valor_total numeric(19,2) NOT NULL,
                                           id_conta_pagar bigint NOT NULL,
                                           id_pessoa bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_compra OWNER TO postgres;



CREATE TABLE public.nota_fiscal_venda (
                                          id bigint NOT NULL,
                                          pdf text NOT NULL,
                                          xml text NOT NULL,
                                          numero_nota_fiscal character varying(255) NOT NULL,
                                          serie character varying(255) NOT NULL,
                                          tipo character varying(255) NOT NULL,
                                          id_venda_compra bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 16775)
-- Name: nota_item_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_item_produto (
                                          id bigint NOT NULL,
                                          quantidade double precision NOT NULL,
                                          id_nota_fiscal_compra bigint NOT NULL,
                                          id_produto bigint NOT NULL
);


ALTER TABLE public.nota_item_produto OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 16780)
-- Name: pessoa_fisica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa_fisica (
                                      id bigint NOT NULL,
                                      email character varying(255) NOT NULL,
                                      nome character varying(255) NOT NULL,
                                      sobrenome character varying(255),
                                      cpf character varying(255) NOT NULL,
                                      data_nascimento date
);


ALTER TABLE public.pessoa_fisica OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 16787)
-- Name: pessoa_juridica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa_juridica (
                                        id bigint NOT NULL,
                                        email character varying(255) NOT NULL,
                                        nome character varying(255) NOT NULL,
                                        sobrenome character varying(255),
                                        categoria character varying(255),
                                        cnpj character varying(255) NOT NULL,
                                        inscricao_estadual character varying(255) NOT NULL,
                                        inscricao_municiapal character varying(255),
                                        nome_fantasia character varying(255) NOT NULL,
                                        razao_social character varying(255) NOT NULL
);


ALTER TABLE public.pessoa_juridica OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 16794)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
                                id bigint NOT NULL,
                                alerta_estoque_baixo_ativo boolean,
                                altura double precision NOT NULL,
                                ativo boolean NOT NULL,
                                descricao_curta character varying(255) NOT NULL,
                                descricao_longa text NOT NULL,
                                largura double precision NOT NULL,
                                nome character varying(255) NOT NULL,
                                peso double precision NOT NULL,
                                profundidade double precision NOT NULL,
                                quantidade_alerta_estoque integer,
                                quantidade_clique integer,
                                quantidade_estoque_atual integer NOT NULL,
                                quantidade_estoque_atual_anterior integer,
                                tipo_unidade character varying(255) NOT NULL,
                                url_video_youtube character varying(255),
                                valor_compra numeric(19,2) NOT NULL,
                                valor_venda numeric(19,2) NOT NULL,
                                id_categoria_produto bigint NOT NULL,
                                id_marca_produto bigint NOT NULL
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16527)
-- Name: seq_acesso; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_acesso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_acesso OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16528)
-- Name: seq_avaliacao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_avaliacao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_avaliacao OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16529)
-- Name: seq_categoria_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_categoria_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_categoria_produto OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16530)
-- Name: seq_conta_pagar; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_pagar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_conta_pagar OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16531)
-- Name: seq_conta_receber; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_receber
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_conta_receber OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16532)
-- Name: seq_cupom_desconto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cupom_desconto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_cupom_desconto OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16533)
-- Name: seq_endereco; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_endereco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_endereco OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16534)
-- Name: seq_forma_pagamento; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_forma_pagamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_forma_pagamento OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16535)
-- Name: seq_imagem_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_imagem_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_imagem_produto OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16536)
-- Name: seq_item_venda; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_item_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_item_venda OWNER TO postgres;

--
-- TOC entry 260 (class 1259 OID 16935)
-- Name: seq_log_sistema; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_log_sistema
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_log_sistema OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16537)
-- Name: seq_marca_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_marca_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_marca_produto OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16538)
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_fiscal_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_nota_fiscal_compra OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16539)
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_fiscal_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16540)
-- Name: seq_nota_item_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_item_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_nota_item_produto OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16541)
-- Name: seq_pessoa; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_pessoa OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16542)
-- Name: seq_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_produto OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16543)
-- Name: seq_status_rastreio; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_status_rastreio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_status_rastreio OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 16544)
-- Name: seq_telefone; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_telefone
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_telefone OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16545)
-- Name: seq_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_usuario OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16546)
-- Name: seq_venda_compra; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_venda_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_venda_compra OWNER TO postgres;

--
-- TOC entry 254 (class 1259 OID 16801)
-- Name: status_rastreio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status_rastreio (
                                        id bigint NOT NULL,
                                        centro_distribuicao character varying(255),
                                        cidade character varying(255),
                                        estado character varying(255),
                                        status character varying(255),
                                        id_venda_compra_loja bigint NOT NULL
);


ALTER TABLE public.status_rastreio OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 16808)
-- Name: telefone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.telefone (
                                 id bigint NOT NULL,
                                 ddd character varying(255) NOT NULL,
                                 numero character varying(255) NOT NULL,
                                 tipo_telefone character varying(255),
                                 id_pessoa bigint NOT NULL
);


ALTER TABLE public.telefone OWNER TO postgres;

--
-- TOC entry 256 (class 1259 OID 16815)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
                                id bigint NOT NULL,
                                data_atual_senha date NOT NULL,
                                login character varying(255) NOT NULL,
                                senha character varying(255) NOT NULL,
                                id_pessoa bigint NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 16822)
-- Name: usuario_acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_acesso (
                                       id_usuario bigint NOT NULL,
                                       id_acesso bigint NOT NULL
);


ALTER TABLE public.usuario_acesso OWNER TO postgres;

--
-- TOC entry 258 (class 1259 OID 16825)
-- Name: venda_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.venda_compra (
                                     id bigint NOT NULL,
                                     data_entrega date,
                                     data_venda date NOT NULL,
                                     quantidade_dias_entrega integer NOT NULL,
                                     valor_desconto numeric(19,2),
                                     valor_frete numeric(19,2) NOT NULL,
                                     valor_total numeric(19,2) NOT NULL,
                                     id_cumpom_desconto bigint,
                                     id_endereco_cobranca bigint,
                                     id_endereco_entrega bigint NOT NULL,
                                     id_forma_pagamento bigint NOT NULL,
                                     id_pessoa bigint NOT NULL
);


ALTER TABLE public.venda_compra OWNER TO postgres;

--
-- TOC entry 3611 (class 0 OID 16698)
-- Dependencies: 237
-- Data for Name: acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3612 (class 0 OID 16703)
-- Dependencies: 238
-- Data for Name: avaliacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.avaliacao (id, data_avaliacao, descricao, nota, id_pessoa, id_produto) VALUES (1, '2024-12-21', 'Produto excelente, com desempenho muito acima do esperado.', 5, 2, 1);


--
-- TOC entry 3613 (class 0 OID 16708)
-- Dependencies: 239
-- Data for Name: categoria_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categoria_produto (id, descricao) VALUES (1, 'Smartphones e Acessórios');
INSERT INTO public.categoria_produto (id, descricao) VALUES (2, 'Notebooks e Computadores');
INSERT INTO public.categoria_produto (id, descricao) VALUES (3, 'Fones de Ouvido');
INSERT INTO public.categoria_produto (id, descricao) VALUES (4, 'Monitores e Displays');
INSERT INTO public.categoria_produto (id, descricao) VALUES (5, 'Teclados e Periféricos');
INSERT INTO public.categoria_produto (id, descricao) VALUES (6, 'Tablets e Dispositivos Móveis');
INSERT INTO public.categoria_produto (id, descricao) VALUES (7, 'Smartwatches e Wearables');
INSERT INTO public.categoria_produto (id, descricao) VALUES (8, 'Carregadores e Cabos');
INSERT INTO public.categoria_produto (id, descricao) VALUES (9, 'Câmeras e Equipamentos de Vídeo');
INSERT INTO public.categoria_produto (id, descricao) VALUES (10, 'Cabos e Conectores');


--
-- TOC entry 3614 (class 0 OID 16713)
-- Dependencies: 240
-- Data for Name: conta_pagar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3615 (class 0 OID 16720)
-- Dependencies: 241
-- Data for Name: conta_receber; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3616 (class 0 OID 16727)
-- Dependencies: 242
-- Data for Name: cupom_desconto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3617 (class 0 OID 16732)
-- Dependencies: 243
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3618 (class 0 OID 16739)
-- Dependencies: 244
-- Data for Name: forma_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3619 (class 0 OID 16744)
-- Dependencies: 245
-- Data for Name: imagem_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3620 (class 0 OID 16751)
-- Dependencies: 246
-- Data for Name: item_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3633 (class 0 OID 16930)
-- Dependencies: 259
-- Data for Name: log_sistema; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3621 (class 0 OID 16756)
-- Dependencies: 247
-- Data for Name: marca_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.marca_produto (id, descricao) VALUES (1, 'Marca XTech');
INSERT INTO public.marca_produto (id, descricao) VALUES (2, 'TurboTech');
INSERT INTO public.marca_produto (id, descricao) VALUES (3, 'PremiumSound');
INSERT INTO public.marca_produto (id, descricao) VALUES (4, 'UltraVision');
INSERT INTO public.marca_produto (id, descricao) VALUES (5, 'GamerPro');
INSERT INTO public.marca_produto (id, descricao) VALUES (6, 'MaxTech');
INSERT INTO public.marca_produto (id, descricao) VALUES (7, 'FitWear');
INSERT INTO public.marca_produto (id, descricao) VALUES (8, 'ChargeMaster');
INSERT INTO public.marca_produto (id, descricao) VALUES (9, 'ActionVision');
INSERT INTO public.marca_produto (id, descricao) VALUES (10, 'CablePro');


--
-- TOC entry 3622 (class 0 OID 16761)
-- Dependencies: 248
-- Data for Name: nota_fiscal_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3623 (class 0 OID 16768)
-- Dependencies: 249
-- Data for Name: nota_fiscal_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3624 (class 0 OID 16775)
-- Dependencies: 250
-- Data for Name: nota_item_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3625 (class 0 OID 16780)
-- Dependencies: 251
-- Data for Name: pessoa_fisica; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pessoa_fisica (id, email, nome, sobrenome, cpf, data_nascimento) VALUES (1, 'joao.silva@example.com', 'João', 'Silva', '12345678900', '1985-04-12');
INSERT INTO public.pessoa_fisica (id, email, nome, sobrenome, cpf, data_nascimento) VALUES (2, 'maria.souza@example.com', 'Maria', 'Souza', '98765432100', '1990-08-25');


--
-- TOC entry 3626 (class 0 OID 16787)
-- Dependencies: 252
-- Data for Name: pessoa_juridica; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3627 (class 0 OID 16794)
-- Dependencies: 253
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (1, true, 15, true, 'Smartphone com 128GB', 'Smartphone de última geração com câmera de 108MP e tela AMOLED.', 7, 'Smartphone XPro', 180, 0.8, 10, 25, 100, 120, 'UN', 'https://youtu.be/exemplo1', 1500.00, 2500.00, 1, 1);
INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (2, true, 30, true, 'Notebook i7 com SSD', 'Notebook com processador Intel i7, 16GB de RAM e 512GB SSD.', 22, 'Notebook Turbo 15', 2200, 1.5, 5, 18, 50, 60, 'UN', 'https://youtu.be/exemplo2', 4500.00, 6000.00, 2, 2);
INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (3, true, 5, true, 'Fone de Ouvido Bluetooth', 'Fone de ouvido sem fio com cancelamento de ruído e 12h de bateria.', 5, 'Fone Premium', 200, 0.2, 20, 40, 200, 230, 'UN', 'https://youtu.be/exemplo3', 200.00, 400.00, 3, 3);
INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (4, true, 10, true, 'Monitor 4K UHD', 'Monitor de alta resolução 4K UHD com 27 polegadas e HDR.', 45, 'Monitor Ultra 27"', 3500, 2.5, 5, 12, 30, 40, 'UN', 'https://youtu.be/exemplo4', 1500.00, 2800.00, 4, 4);
INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (5, true, 12, true, 'Teclado Mecânico RGB', 'Teclado mecânico com iluminação RGB, switches táteis e anti-ghosting.', 20, 'Teclado Gamer Pro', 900, 0.7, 10, 30, 70, 80, 'UN', 'https://youtu.be/exemplo5', 300.00, 700.00, 5, 5);
INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (6, true, 20, true, 'Tablet com Caneta', 'Tablet de alta performance com suporte para caneta digital e multitarefa.', 15, 'Tablet Max 10', 800, 0.5, 8, 22, 40, 50, 'UN', 'https://youtu.be/exemplo6', 1200.00, 2200.00, 6, 6);
INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (7, true, 25, true, 'Smartwatch com GPS', 'Relógio inteligente com monitoramento de saúde, GPS e resistência à água.', 10, 'Smartwatch Fit', 150, 0.3, 10, 35, 150, 160, 'UN', 'https://youtu.be/exemplo7', 500.00, 1000.00, 7, 7);
INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (8, true, 7, true, 'Carregador Turbo USB-C', 'Carregador rápido com tecnologia USB-C para dispositivos compatíveis.', 5, 'Carregador Turbo', 100, 0.2, 15, 50, 300, 320, 'UN', 'https://youtu.be/exemplo8', 50.00, 120.00, 8, 8);
INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (9, true, 12, true, 'Câmera de Ação 4K', 'Câmera de ação compacta com gravação em 4K e resistência a impactos.', 10, 'Action Cam Go', 400, 0.6, 10, 25, 25, 30, 'UN', 'https://youtu.be/exemplo9', 800.00, 1600.00, 9, 9);
INSERT INTO public.produto (id, alerta_estoque_baixo_ativo, altura, ativo, descricao_curta, descricao_longa, largura, nome, peso, profundidade, quantidade_alerta_estoque, quantidade_clique, quantidade_estoque_atual, quantidade_estoque_atual_anterior, tipo_unidade, url_video_youtube, valor_compra, valor_venda, id_categoria_produto, id_marca_produto) VALUES (10, true, 3, true, 'Cabo HDMI 2.1', 'Cabo HDMI de alta velocidade com suporte a 4K e 8K.', 10, 'Cabo HDMI Premium', 250, 0.1, 20, 60, 500, 520, 'UN', 'https://youtu.be/exemplo10', 30.00, 90.00, 10, 10);


--
-- TOC entry 3628 (class 0 OID 16801)
-- Dependencies: 254
-- Data for Name: status_rastreio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3629 (class 0 OID 16808)
-- Dependencies: 255
-- Data for Name: telefone; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.telefone (id, ddd, numero, tipo_telefone, id_pessoa) VALUES (1, '11', '999999999', 'WHATSAPP', 1);


--
-- TOC entry 3630 (class 0 OID 16815)
-- Dependencies: 256
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (id, data_atual_senha, login, senha, id_pessoa) VALUES (1, '2024-12-22', 'wolf', '123', 1);


--
-- TOC entry 3631 (class 0 OID 16822)
-- Dependencies: 257
-- Data for Name: usuario_acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3632 (class 0 OID 16825)
-- Dependencies: 258
-- Data for Name: venda_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3642 (class 0 OID 0)
-- Dependencies: 217
-- Name: seq_acesso; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_acesso', 1, false);


--
-- TOC entry 3643 (class 0 OID 0)
-- Dependencies: 218
-- Name: seq_avaliacao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_avaliacao', 1, false);


--
-- TOC entry 3644 (class 0 OID 0)
-- Dependencies: 219
-- Name: seq_categoria_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_categoria_produto', 1, false);


--
-- TOC entry 3645 (class 0 OID 0)
-- Dependencies: 220
-- Name: seq_conta_pagar; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_pagar', 1, false);


--
-- TOC entry 3646 (class 0 OID 0)
-- Dependencies: 221
-- Name: seq_conta_receber; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_receber', 1, false);


--
-- TOC entry 3647 (class 0 OID 0)
-- Dependencies: 222
-- Name: seq_cupom_desconto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_cupom_desconto', 1, false);


--
-- TOC entry 3648 (class 0 OID 0)
-- Dependencies: 223
-- Name: seq_endereco; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_endereco', 1, false);


--
-- TOC entry 3649 (class 0 OID 0)
-- Dependencies: 224
-- Name: seq_forma_pagamento; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_forma_pagamento', 1, false);


--
-- TOC entry 3650 (class 0 OID 0)
-- Dependencies: 225
-- Name: seq_imagem_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_imagem_produto', 1, false);


--
-- TOC entry 3651 (class 0 OID 0)
-- Dependencies: 226
-- Name: seq_item_venda; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_item_venda', 1, false);


--
-- TOC entry 3652 (class 0 OID 0)
-- Dependencies: 260
-- Name: seq_log_sistema; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_log_sistema', 1, false);


--
-- TOC entry 3653 (class 0 OID 0)
-- Dependencies: 227
-- Name: seq_marca_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_marca_produto', 1, false);


--
-- TOC entry 3654 (class 0 OID 0)
-- Dependencies: 228
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_compra', 1, false);


--
-- TOC entry 3655 (class 0 OID 0)
-- Dependencies: 229
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_venda', 1, false);


--
-- TOC entry 3656 (class 0 OID 0)
-- Dependencies: 230
-- Name: seq_nota_item_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_item_produto', 1, false);


--
-- TOC entry 3657 (class 0 OID 0)
-- Dependencies: 231
-- Name: seq_pessoa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_pessoa', 1, false);


--
-- TOC entry 3658 (class 0 OID 0)
-- Dependencies: 232
-- Name: seq_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_produto', 1, false);


--
-- TOC entry 3659 (class 0 OID 0)
-- Dependencies: 233
-- Name: seq_status_rastreio; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_status_rastreio', 1, false);


--
-- TOC entry 3660 (class 0 OID 0)
-- Dependencies: 234
-- Name: seq_telefone; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_telefone', 1, false);


--
-- TOC entry 3661 (class 0 OID 0)
-- Dependencies: 235
-- Name: seq_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_usuario', 1, false);


--
-- TOC entry 3662 (class 0 OID 0)
-- Dependencies: 236
-- Name: seq_venda_compra; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_venda_compra', 1, false);


--
-- TOC entry 3370 (class 2606 OID 16702)
-- Name: acesso acesso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT acesso_pkey PRIMARY KEY (id);


--
-- TOC entry 3372 (class 2606 OID 16707)
-- Name: avaliacao avaliacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT avaliacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3374 (class 2606 OID 16712)
-- Name: categoria_produto categoria_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_produto
    ADD CONSTRAINT categoria_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3376 (class 2606 OID 16719)
-- Name: conta_pagar conta_pagar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta_pagar
    ADD CONSTRAINT conta_pagar_pkey PRIMARY KEY (id);


--
-- TOC entry 3378 (class 2606 OID 16726)
-- Name: conta_receber conta_receber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta_receber
    ADD CONSTRAINT conta_receber_pkey PRIMARY KEY (id);


--
-- TOC entry 3380 (class 2606 OID 16731)
-- Name: cupom_desconto cupom_desconto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupom_desconto
    ADD CONSTRAINT cupom_desconto_pkey PRIMARY KEY (id);


--
-- TOC entry 3382 (class 2606 OID 16738)
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 3384 (class 2606 OID 16743)
-- Name: forma_pagamento forma_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forma_pagamento
    ADD CONSTRAINT forma_pagamento_pkey PRIMARY KEY (id);


--
-- TOC entry 3386 (class 2606 OID 16750)
-- Name: imagem_produto imagem_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT imagem_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3388 (class 2606 OID 16755)
-- Name: item_venda item_venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda
    ADD CONSTRAINT item_venda_pkey PRIMARY KEY (id);


--
-- TOC entry 3416 (class 2606 OID 16934)
-- Name: log_sistema log_sistema_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log_sistema
    ADD CONSTRAINT log_sistema_pkey PRIMARY KEY (id);


--
-- TOC entry 3390 (class 2606 OID 16760)
-- Name: marca_produto marca_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca_produto
    ADD CONSTRAINT marca_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3392 (class 2606 OID 16767)
-- Name: nota_fiscal_compra nota_fiscal_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT nota_fiscal_compra_pkey PRIMARY KEY (id);


--
-- TOC entry 3394 (class 2606 OID 16774)
-- Name: nota_fiscal_venda nota_fiscal_venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT nota_fiscal_venda_pkey PRIMARY KEY (id);


--
-- TOC entry 3396 (class 2606 OID 16779)
-- Name: nota_item_produto nota_item_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_item_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3398 (class 2606 OID 16786)
-- Name: pessoa_fisica pessoa_fisica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_pkey PRIMARY KEY (id);


--
-- TOC entry 3400 (class 2606 OID 16793)
-- Name: pessoa_juridica pessoa_juridica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id);


--
-- TOC entry 3402 (class 2606 OID 16800)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3404 (class 2606 OID 16807)
-- Name: status_rastreio status_rastreio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT status_rastreio_pkey PRIMARY KEY (id);


--
-- TOC entry 3406 (class 2606 OID 16814)
-- Name: telefone telefone_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT telefone_pkey PRIMARY KEY (id);


--
-- TOC entry 3410 (class 2606 OID 16831)
-- Name: usuario_acesso uk_4664573x50o7fa5quyvp6mck9; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_acesso
    ADD CONSTRAINT uk_4664573x50o7fa5quyvp6mck9 UNIQUE (id_acesso);


--
-- TOC entry 3412 (class 2606 OID 16833)
-- Name: usuario_acesso unique_acesso_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_acesso
    ADD CONSTRAINT unique_acesso_user UNIQUE (id_usuario, id_acesso);


--
-- TOC entry 3408 (class 2606 OID 16821)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 3414 (class 2606 OID 16829)
-- Name: venda_compra venda_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda_compra
    ADD CONSTRAINT venda_compra_pkey PRIMARY KEY (id);


--
-- TOC entry 3442 (class 2620 OID 16950)
-- Name: usuario log_usuario_update_delete; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER log_usuario_update_delete AFTER DELETE OR UPDATE ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.registrar_log_sistema();


--
-- TOC entry 3436 (class 2620 OID 16927)
-- Name: avaliacao validachavepessoaavaliacao; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaavaliacao BEFORE INSERT OR UPDATE ON public.avaliacao FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3437 (class 2620 OID 16945)
-- Name: conta_pagar validachavepessoacontapagar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagar BEFORE INSERT OR UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3439 (class 2620 OID 16944)
-- Name: conta_receber validachavepessoacontareceber; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontareceber BEFORE INSERT OR UPDATE ON public.conta_receber FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3440 (class 2620 OID 16929)
-- Name: endereco validachavepessoaendereco; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaendereco BEFORE INSERT OR UPDATE ON public.endereco FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3438 (class 2620 OID 16948)
-- Name: conta_pagar validachavepessoafornecedor; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoafornecedor BEFORE INSERT OR UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoaforn();


--
-- TOC entry 3445 (class 2620 OID 16943)
-- Name: log_sistema validachavepessoalog; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoalog BEFORE INSERT OR UPDATE ON public.log_sistema FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3441 (class 2620 OID 16928)
-- Name: telefone validachavepessoatelefone; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoatelefone BEFORE INSERT OR UPDATE ON public.telefone FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3443 (class 2620 OID 16942)
-- Name: usuario validachavepessoausuario; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoausuario BEFORE INSERT OR UPDATE ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3444 (class 2620 OID 16941)
-- Name: venda_compra validachavepessoavendacompra; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoavendacompra BEFORE INSERT OR UPDATE ON public.venda_compra FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3429 (class 2606 OID 16894)
-- Name: usuario_acesso acesso_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_acesso
    ADD CONSTRAINT acesso_fk FOREIGN KEY (id_acesso) REFERENCES public.acesso(id);


--
-- TOC entry 3418 (class 2606 OID 16839)
-- Name: conta_pagar conta_pagar_forma_pagamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta_pagar
    ADD CONSTRAINT conta_pagar_forma_pagamento_fk FOREIGN KEY (id_forma_pagamento) REFERENCES public.forma_pagamento(id);


--
-- TOC entry 3417 (class 2606 OID 16834)
-- Name: avaliacao fk_avaliacao_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT fk_avaliacao_produto FOREIGN KEY (id_produto) REFERENCES public.produto(id);


--
-- TOC entry 3426 (class 2606 OID 16879)
-- Name: produto fk_categoria_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT fk_categoria_produto FOREIGN KEY (id_categoria_produto) REFERENCES public.categoria_produto(id);


--
-- TOC entry 3422 (class 2606 OID 16859)
-- Name: nota_fiscal_compra fk_conta_pagar_nfe_compra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT fk_conta_pagar_nfe_compra FOREIGN KEY (id_conta_pagar) REFERENCES public.conta_pagar(id);


--
-- TOC entry 3431 (class 2606 OID 16904)
-- Name: venda_compra fk_cupom_desconto_compra_venda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda_compra
    ADD CONSTRAINT fk_cupom_desconto_compra_venda FOREIGN KEY (id_cumpom_desconto) REFERENCES public.cupom_desconto(id);


--
-- TOC entry 3432 (class 2606 OID 16909)
-- Name: venda_compra fk_endereco_cobraca_compra_venda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda_compra
    ADD CONSTRAINT fk_endereco_cobraca_compra_venda FOREIGN KEY (id_endereco_cobranca) REFERENCES public.endereco(id);


--
-- TOC entry 3433 (class 2606 OID 16914)
-- Name: venda_compra fk_endereco_entrega_compra_venda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda_compra
    ADD CONSTRAINT fk_endereco_entrega_compra_venda FOREIGN KEY (id_endereco_entrega) REFERENCES public.endereco(id);


--
-- TOC entry 3434 (class 2606 OID 16919)
-- Name: venda_compra fk_forma_pagamento_venda_compra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda_compra
    ADD CONSTRAINT fk_forma_pagamento_venda_compra FOREIGN KEY (id_forma_pagamento) REFERENCES public.forma_pagamento(id);


--
-- TOC entry 3420 (class 2606 OID 16854)
-- Name: item_venda fk_item_venda_compra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda
    ADD CONSTRAINT fk_item_venda_compra FOREIGN KEY (id_venda_compra) REFERENCES public.venda_compra(id);


--
-- TOC entry 3421 (class 2606 OID 16849)
-- Name: item_venda fk_item_venda_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda
    ADD CONSTRAINT fk_item_venda_produto FOREIGN KEY (id_produto) REFERENCES public.produto(id);


--
-- TOC entry 3435 (class 2606 OID 16936)
-- Name: log_sistema fk_log_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log_sistema
    ADD CONSTRAINT fk_log_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);


--
-- TOC entry 3427 (class 2606 OID 16884)
-- Name: produto fk_marca_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT fk_marca_produto FOREIGN KEY (id_marca_produto) REFERENCES public.marca_produto(id);


--
-- TOC entry 3424 (class 2606 OID 16869)
-- Name: nota_item_produto fk_nfe_compra_item; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT fk_nfe_compra_item FOREIGN KEY (id_nota_fiscal_compra) REFERENCES public.nota_item_produto(id);


--
-- TOC entry 3419 (class 2606 OID 16844)
-- Name: imagem_produto fk_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES public.produto(id);


--
-- TOC entry 3425 (class 2606 OID 16874)
-- Name: nota_item_produto fk_produto_nota_item; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT fk_produto_nota_item FOREIGN KEY (id_produto) REFERENCES public.nota_item_produto(id);


--
-- TOC entry 3428 (class 2606 OID 16889)
-- Name: status_rastreio fk_venda_compra_loja; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT fk_venda_compra_loja FOREIGN KEY (id_venda_compra_loja) REFERENCES public.venda_compra(id);


--
-- TOC entry 3423 (class 2606 OID 16864)
-- Name: nota_fiscal_venda fk_venda_compra_nfe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT fk_venda_compra_nfe FOREIGN KEY (id_venda_compra) REFERENCES public.venda_compra(id);


--
-- TOC entry 3430 (class 2606 OID 16899)
-- Name: usuario_acesso usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_acesso
    ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);


-- Completed on 2024-12-23 20:59:39 -03

--
-- PostgreSQL database dump complete
--
