create table estado
(
  id   int auto_increment
    primary key,
  UF   varchar(2)  not null,
  nome varchar(20) null
);

create table cidade
(
  id        int auto_increment
    primary key,
  nome      varchar(100) null,
  estado_id int          not null,
  constraint fk_cidade_estado1
    foreign key (estado_id) references estado (id)
);

create index fk_cidade_estado1_idx
  on cidade (estado_id);

create table endereco
(
  id          int auto_increment
    primary key,
  rua         varchar(45)  null,
  bairro      varchar(45)  null,
  numero      int          null,
  complemento varchar(255) null,
  cidade_id   int          not null,
  constraint fk_endereco_cidade1
    foreign key (cidade_id) references cidade (id)
);

create index fk_endereco_cidade1_idx
  on endereco (cidade_id);

create table universidade
(
  id          int auto_increment
    primary key,
  sigla       varchar(10) null,
  nome        varchar(45) null,
  endereco_id int         not null,
  constraint fk_universidade_endereco1
    foreign key (endereco_id) references endereco (id)
);

create table curso
(
  id              int auto_increment
    primary key,
  nome            varchar(45) null,
  turno           varchar(45) null,
  universidade_id int         not null,
  constraint fk_curso_universidade1
    foreign key (universidade_id) references universidade (id)
);

create table aluno
(
  id           int auto_increment
    primary key,
  cpf          varchar(14)  null,
  curso_id     int          not null,
  ano_ingresso int          not null,
  nome         varchar(100) not null,
  constraint fk_aluno_curso1
    foreign key (curso_id) references curso (id)
);

create index fk_aluno_curso1_idx
  on aluno (curso_id);

create index fk_curso_universidade1_idx
  on curso (universidade_id);

create index fk_universidade_endereco1_idx
  on universidade (endereco_id);

create table usuario
(
  login varchar(16)  not null
    primary key,
  email varchar(100) not null,
  senha varchar(16)  not null,
  nivel int          null
);


