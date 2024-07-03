-- Use a base de dados existente
USE publicador_db;

-- Criar tabela USUARIO_SISTEMA
CREATE TABLE USUARIO_SISTEMA (
    ID_USUARIO_SISTEMA BIGINT NOT NULL AUTO_INCREMENT,
    LOGIN VARCHAR(255) NOT NULL,
    CHAVE_ACESSO VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID_USUARIO_SISTEMA)
);

-- Criar tabela MENSAGEM_PENDENTE
CREATE TABLE MENSAGEM_PENDENTE (
    ID_MENSAGEM_PENDENTE BIGINT NOT NULL AUTO_INCREMENT,
    NUMERO_PROCESSO VARCHAR(255) NOT NULL,
    MENSAGEM TEXT NOT NULL,
    ID_USUARIO_SISTEMA BIGINT,
    PRIMARY KEY (ID_MENSAGEM_PENDENTE),
    CONSTRAINT FK_MENSAGEM_PENDENTE_USUARIO_SISTEMA 
    FOREIGN KEY (ID_USUARIO_SISTEMA) REFERENCES USUARIO_SISTEMA(ID_USUARIO_SISTEMA)
);