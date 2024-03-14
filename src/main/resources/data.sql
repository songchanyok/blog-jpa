drop table if exists article;
CREATE TABLE article  (
    id BIGINT AUTO_INCREMENT primary key,
    title varchar(255) not null,
    content varchar(255) not null,
    create_date timestamp not null,
    update_date timestamp not null
);

INSERT INTO article (title, content,create_date,update_date) VALUES ('블로그 제목', '블로그 내용',now(),now());
INSERT INTO article (title, content,create_date,update_date) VALUES ('블로그 제목2', '블로그 내용2',now(),now());
INSERT INTO article (title, content,create_date,update_date) VALUES ('블로그 제목3', '블로그 내용3',now(),now());