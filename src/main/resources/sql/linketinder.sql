CREATE TABLE candidatos (
  id serial PRIMARY KEY,
  nome varchar NOT NULL,
  sobrenome varchar NOT NULL,
  dataNascimento date NOT NULL,
  email varchar NOT NULL UNIQUE ,
  redeSocial varchar NOT NULL,
  telefone varchar NOT NULL,
  cpf varchar NOT NULL,
  pais varchar NOT NULL,
  cep varchar NOT NULL,
  descricao varchar NOT NULL,
  senha varchar NOT NULL
);

CREATE TABLE empresas (
  id serial PRIMARY KEY,
  nome varchar NOT NULL,
  cnpj varchar NOT NULL UNIQUE,
  email varchar NOT NULL UNIQUE,
  descricao varchar NOT NULL,
  pais varchar NOT NULL,
  cep varchar NOT NULL,
  senha varchar NOT NULL
);

CREATE TABLE competencias (
  id serial PRIMARY KEY,
  nome varchar NOT NULL
);

CREATE TABLE nivel_formacao (
    id serial PRIMARY KEY,
    nivel varchar
);

CREATE TABLE nivel_experiencia (
    id serial PRIMARY KEY,
    nivel varchar
);

CREATE TABLE curtidas_status (
  id serial PRIMARY KEY,
  status varchar NOT NULL
);

CREATE TABLE vagas (
  id serial PRIMARY KEY,
  idEmpresa int NOT NULL REFERENCES empresas (id),
  nome varchar NOT NULL,
  descricao varchar NOT NULL,
  cidade varchar NOT NULL,
  idNivelFormacao int NOT NULL REFERENCES nivel_formacao (id),
  idNivelExperiencia int NOT NULL REFERENCES nivel_experiencia (id)
);

CREATE TABLE nivel_competencia (
    id serial PRIMARY KEY,
    nivel varchar
);

CREATE TABLE candidato_competencia (
  id serial PRIMARY KEY,
  idCandidato int NOT NULL REFERENCES candidatos (id),
  idCompetencia int NOT NULL REFERENCES competencias (id),
  idNivelCompetencia int  NOT NULL REFERENCES nivel_competencia (id)
);

CREATE TABLE vaga_competencia (
  id serial PRIMARY KEY,
  idVaga int NOT NULL REFERENCES vagas (id),
  idCompetencia int NOT NULL REFERENCES competencias (id),
  idNivelCompetencia int NOT NULL REFERENCES nivel_competencia (id)
);

CREATE TABLE formacoes (
  id serial PRIMARY KEY,
  idCandidato int NOT NULL REFERENCES candidatos (id),
  instituicao varchar NOT NULL,
  curso varchar NOT NULL,
  anoConclusao varchar NOT NULL,
  idNivelFormacao int NOT NULL REFERENCES nivel_formacao (id)
);

CREATE TABLE experiencias (
  id serial PRIMARY KEY,
  idCandidato int NOT NULL REFERENCES candidatos (id),
  cargo varchar NOT NULL,
  empresa varchar NOT NULL,
  idNivelExperiencia int NOT NULL REFERENCES nivel_experiencia (id)
);

CREATE TABLE curtidas (
  id serial PRIMARY KEY,
  idCandidato int NOT NULL REFERENCES candidatos (id),
  idVaga int REFERENCES vagas (id),
  idEmpresa int REFERENCES empresas (id),
  idStatus int REFERENCES curtidas_status (id)
);

-- Inserir valores na tabela curtidas_status
INSERT INTO curtidas_status (status)
VALUES
    ('AGUARDO'),
    ('MATCH'),
    ('NO_MATCH');

-- Inserir valores na tabela nivel_formacao
INSERT INTO nivel_formacao (nivel)
VALUES
  ('Graduação'),
  ('Pós-Graduação'),
  ('Mestrado'),
  ('Doutorado');

-- Inserir valores na tabela nivel_experiencia
INSERT INTO nivel_experiencia (nivel)
VALUES
  ('Estágio'),
  ('Junior'),
  ('Pleno'),
  ('Senior'),
  ('Gerente');

-- Inserir valores na tabela nivel_competencia
    INSERT INTO nivel_competencia (nivel)
    VALUES
      ('Iniciante'),
      ('Intermediário'),
      ('Avançado');


-- Inserir Candidatos
INSERT INTO candidatos (nome, sobrenome, dataNascimento, email, redeSocial ,telefone , cpf, pais, cep, descricao, senha)
VALUES
  ('Candidato 1', 'Sobrenome 1', '1990-01-01', 'candidato1@email.com','lindln','11111111111', '123.456.789-00', 'Brasil', '12345-678', 'Descrição do Candidato 1', 'senha123'),
  ('Candidato 2', 'Sobrenome 2', '1985-05-10', 'candidato2@email.com','lindln','11111111111', '987.654.321-00', 'Brasil', '98765-432', 'Descrição do Candidato 2', 'senha456'),
  ('Candidato 3', 'Sobrenome 3', '1998-08-20', 'candidato3@email.com','lindln','11111111111', '555.555.555-00', 'Brasil', '55555-555', 'Descrição do Candidato 3', 'senha789'),
  ('Candidato 4', 'Sobrenome 4', '1992-03-15', 'candidato4@email.com','lindln','11111111111', '111.111.111-00', 'Brasil', '11111-111', 'Descrição do Candidato 4', 'senhaabc'),
  ('Candidato 5', 'Sobrenome 5', '1987-11-30', 'candidato5@email.com','lindln','11111111111', '999.999.999-00', 'Brasil', '99999-999', 'Descrição do Candidato 5', 'senhaxyz');

INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha)
VALUES
  ('Empresa A', '123456789', 'empresa.a@example.com', 'Descrição da Empresa A', 'Brasil', '12345-678', 'senha123'),
  ('Empresa B', '987654321', 'empresa.b@example.com', 'Descrição da Empresa B', 'Brasil', '98765-432', 'senha456'),
  ('Empresa C', '555555555', 'empresa.c@example.com', 'Descrição da Empresa C', 'Brasil', '55555-555', 'senha789'),
  ('Empresa D', '111111111', 'empresa.d@example.com', 'Descrição da Empresa D', 'Brasil', '11111-111', 'senhaabc'),
  ('Empresa E', '999999999', 'empresa.e@example.com', 'Descrição da Empresa E', 'Brasil', '99999-999', 'senhaxyz');

-- Inserção de vagas
INSERT INTO vagas (idEmpresa, nome, descricao, cidade, idNivelFormacao, idNivelExperiencia)
VALUES
(1, 'Desenvolvedor Java', 'Desenvolvimento de aplicativos Java', 'São Paulo', 1, 2),
(2, 'Designer Gráfico', 'Criação de materiais visuais', 'Rio de Janeiro', 3, 4),
(3, 'Analista de Dados', 'Análise de dados para tomada de decisões', 'Belo Horizonte', 1, 5),
(4, 'Engenheiro de Software', 'Desenvolvimento de software para sistemas complexos', 'Porto Alegre', 2, 1),
(5, 'Gerente de Projetos', 'Gestão de projetos de grande porte', 'Curitiba', 3, 2);

