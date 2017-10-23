INSERT INTO grupos (id, descricao, nome) VALUES (1, 'Administradores', 'ADMINISTRADORES');
INSERT INTO grupos (id, descricao, nome) VALUES (2, 'Clientes', 'CLIENTES');


INSERT INTO departamentos (id,nome) VALUES (1, 'Desenvolvimento');
INSERT INTO departamentos (id,nome) VALUES (2, 'Suporte');
INSERT INTO departamentos (id,nome) VALUES (3, 'Vendas');
INSERT INTO departamentos (id,nome) VALUES (4, 'Administrativo');
INSERT INTO departamentos (id,nome) VALUES (5, 'Gestão de Qualidade');

INSERT INTO usuarios (id,email,nome,primeiro_nome,senha,status,departamento_id,dt_cadastro, telefone1, telefone2) VALUES (1,'vinydos3@gmail.com', 'Ferreira Paulino','Vinicius','81dc9bdb52d04dc20036dbd8313ed055',1,1,'2016/07/18','66996503080','66996503080');
INSERT INTO usuarios (id,email,nome,primeiro_nome,senha,status,departamento_id,dt_cadastro, telefone1, telefone2) VALUES (2,'cliente@gmail.com', 'Naja','Cliente','81dc9bdb52d04dc20036dbd8313ed055',1,5,'2016/07/18','6695632598','6696569854');


INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (1,1);
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (2,2);


INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (1,   1,  'Administrador', NULL, '','');
INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (2,   2,  'Busca', NULL, '','');
INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (3,   3,  'Pedidos', NULL, '','');
INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (4,   1,  'Menus', 1, '/administrador/cadMenu.xhtml','ui-icon-home');
INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (5,   5,  'Usuarios', 3, '/pedidos/cadUsuarios.xhtml','ui-icon-person');
INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (6,   3,  'Produtos', 1, '/administrador/CadastroProduto.xhtml','ui-icon-home');
INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (7,   4,  'Grupos', 1, '/administrador/cadGrupo.xhtml','ui-icon-home');
INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (8,   5,  'Busca Produtos', 1, '/administrador/PesquisaProdutos.xhtml','ui-icon-search');
INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (11,  8,  'Busca Produtos', 2, '/administrador/PesquisaProdutos.xhtml','ui-icon-search');


INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (14,  1,  'Buscar Pedidos', 3, '/pedidos/PesquisaPedidos.xhtml','ui-icon-search');
INSERT INTO menus (id,nivel,nome,pai_id,url,icone) VALUES (15,  2,  'Novo Pedido',3,'/pedidos/CadastroPedido.xhtml','ui-icon-cart');




INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (1,1);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (1,2);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (1,3);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (1,4);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (1,5);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (1,6);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (1,7);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (1,8);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (1,15);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (2,3);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (2,5);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (2,14);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (2,15);
INSERT INTO grupo_menu (GRUPO_ID,MENU_ID) VALUES (2,11);


alter table categoria auto_increment = 1;

insert into categoria (descricao) values ('Comida');
insert into categoria (descricao) values ('Bebidas');
insert into categoria (descricao) values ('Frutas');
insert into categoria (descricao) values ('Perfumaria');

insert into categoria (descricao, categoria_pai_id) values ('Farinaceo', 1);
insert into categoria (descricao, categoria_pai_id) values ('Mercearia Diversa', 1);
insert into categoria (descricao, categoria_pai_id) values ('Feijao', 1);
insert into categoria (descricao, categoria_pai_id) values ('Oleos', 1);
insert into categoria (descricao, categoria_pai_id) values ('Carnes', 1);
insert into categoria (descricao, categoria_pai_id) values ('Embutidos', 1);


insert into categoria (descricao, categoria_pai_id) values ('Cerveja', 2);
insert into categoria (descricao, categoria_pai_id) values ('Refrigerantes', 2);
insert into categoria (descricao, categoria_pai_id) values ('Vodka', 2);
insert into categoria (descricao, categoria_pai_id) values ('Whisky', 2);
insert into categoria (descricao, categoria_pai_id) values ('Energetico', 2);

insert into categoria (descricao, categoria_pai_id) values ('Frutas', 3);
insert into categoria (descricao, categoria_pai_id) values ('Legumes', 3);

insert into categoria (descricao, categoria_pai_id) values ('Perfumes', 4);


insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('01', 'Arroz', 10, 1);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('02', 'Feijão', 8, 3);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('03', 'Oleo', 5, 4);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('04', 'Vinagre', 3.5, 2);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('05', 'Cerveja', 5, 7);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('06', 'Maça', 1.2, 12);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('07', 'Picanha', 25, 5);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('08', 'Pão', 6.5, 2);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('09', 'Presunto', 18, 6);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('10', 'Queijo', 22, 6);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('11', 'Leite Condensado', 3.5, 2);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('12', 'Coca Cola', 8, 8);
insert into produto (codigoProduto, nome, valor_unitario, categoria_id) values ('13', 'Perfume', 60, 14);




insert into pedido (id, data_criacao, data_entrega, entrega_cep, entrega_cidade, entrega_complemento, entrega_logradouro, entrega_numero, entrega_uf, forma_pagamento, observacao, status, valor_total, cliente_id) VALUES ('1', '2016-10-13 08:00:00', '2016-10-21', '78735125', 'Ronodonopolis', NULL, 'Rua Sirlei Barbosa Barbieri', '818', 'MT', 'DINHEIRO', 'INSERIDO MANUALMENTE', 'ORCAMENTO', '500', '1');