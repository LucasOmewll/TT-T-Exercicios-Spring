create table tarefas(
    id bigint not null primary key auto_increment,
    descricao varchar(180) not null,
    completa tinyint not null
)