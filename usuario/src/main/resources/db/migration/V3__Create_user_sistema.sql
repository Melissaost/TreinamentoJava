-- V3__Create_user_sistema.sql

-- Insere o usuário se não existir
INSERT INTO treinamento_java_db.USUARIO (LOGIN, SENHA, NOME, CPF, ENDERECO_ID)
SELECT 'sistema@email.com', '$2a$10$0aG6OJomAyoy39vwB36DSebDV4ubFqVQ/z5q88h6PAeaLGqdQKf2i', 'Sistema Notificacao', '11111111111', null
    WHERE NOT EXISTS (
    SELECT 1 FROM treinamento_java_db.USUARIO WHERE LOGIN = 'sistema@email.com'
);