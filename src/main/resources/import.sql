insert into cozinha (nome) values ('Italiana');
insert into cozinha (nome) values ('Japonesa');
insert into cozinha (nome) values ('Brasileira');
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Casa Nostra', 10.0,1)
insert into restaurante (nome, taxa_frete,cozinha_id) values ('JapaFood', 15.0,2)
insert into restaurante (nome, taxa_frete,cozinha_id) values ('Japa Mix',10.0,2)
insert into forma_pagamento (descricao) values ("Dinheiro")
insert into forma_pagamento (descricao) values ("Cartao")
insert into permissao (nome, descricao) values ("Produto","Permisao de leitura para objeto produto")
insert into estado (nome) values ('Amazonas');
insert into estado (nome) values ('Pará');
insert into cidade (nome, estado_id) values ('Manaus', 1);
insert into cidade (nome, estado_id) values ('Belém', 2);

