create table rh.tb_departamento (id int8 generated by default as identity, nome varchar(255), primary key (id));
create table rh.tb_funcao (id int8 generated by default as identity, nome varchar(255), salario float8, primary key (id));
create table rh.tb_funcionario (id int8 generated by default as identity, celular varchar(255), email varchar(255), nome varchar(255), primary key (id));
