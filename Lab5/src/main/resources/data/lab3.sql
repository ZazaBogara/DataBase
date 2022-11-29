CREATE DATABASE IF NOT EXISTS mosorinzakharlab4;
USE mosorinzakharlab4;

DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS chat_has_user; 
DROP TABLE IF EXISTS chat;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS server;

CREATE TABLE server(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, 
name VARCHAR(45) NOT NULL
) ENGINE = INNODB;

CREATE TABLE user(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, 
name VARCHAR(45) NOT NULL
) ENGINE = INNODB;

CREATE TABLE chat(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, 
name VARCHAR(45) NOT NULL,
server_id INT NOT NULL
) ENGINE = INNODB;

CREATE TABLE chat_has_user(
chat_id INT NOT NULL, 
user_id INT NOT NULL, 
PRIMARY KEY(chat_id, user_id)
) ENGINE = INNODB;


CREATE TABLE message(
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, 
text VARCHAR(1024),
photo BLOB,
audio MEDIUMBLOB,
chat_id INT NOT NULL, 
user_id INT NOT NULL,
time DATE
) ENGINE = INNODB;

ALTER TABLE chat
ADD CONSTRAINT FK_chat_server_id
FOREIGN KEY (server_id)
REFERENCES server (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE chat_has_user
ADD CONSTRAINT FK_chat_has_user_chat_id
FOREIGN KEY (chat_id)
REFERENCES chat (id)
ON DELETE CASCADE
ON UPDATE CASCADE,
ADD CONSTRAINT FK_chat_has_user_user_id
FOREIGN KEY (user_id)
REFERENCES user (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE message
ADD CONSTRAINT FK_message_chat_id
FOREIGN KEY (chat_id)
REFERENCES chat (id)
ON DELETE CASCADE
ON UPDATE CASCADE,
ADD CONSTRAINT FK_message_user_id
FOREIGN KEY (user_id)
REFERENCES user (id)
ON DELETE CASCADE
ON UPDATE CASCADE;


CREATE INDEX name_idx
ON chat (name ASC);
CREATE INDEX name_idx
ON user (name ASC);
CREATE INDEX name_idx
ON server (name ASC);
CREATE INDEX time_idx
ON message (time ASC);




INSERT INTO server(name) VALUES
('one'),
('two'),
('three'),
('four'),
('five'),
('six'),
('seven'),
('eight'),
('nine'),
('ten');

INSERT INTO user(name) VALUES
('petya'),
('vasya'),
('zakhar'),
('pavlo'),
('vlad'),
('vova'),
('petro'),
('anya'),
('nastya'),
('olga');

INSERT INTO chat(name, server_id) VALUES
('chatik_1', 1),
('chatik_2', 1),
('chatik_3', 1),
('chatik_4', 1),
('chatik_5', 1),
('chatik_6', 1),
('chatik_1', 2),
('chatik_2', 2),
('chatik_3', 2),
('chatik_4', 2),
('chatik_10', 3),
('chatik_9', 3),
('chatik_1', 5),
('chatik_2', 5),
('chatik_3', 5);

INSERT INTO chat_has_user(chat_id, user_id) VALUES
(1,1),
(1,3),
(1,2),
(2,7),
(2,6),
(3,2),
(4,2),
(5,3),
(6,5),
(1,4),
(7,6),
(8,2),
(2,2),
(3,4);

INSERT INTO message(chat_id, user_id, text, time) VALUES
(1, 1, 'do', '2000-09-20'),
(1, 2, 'ofc', '2000-09-20'),
(1, 1, 'we should do 5 labs', '2000-09-22'),
(1, 1, 'wtf', '2000-09-23'),
(1, 2, 'never mind', '2000-10-23'),
(1, 3, 'lets win', '2000-10-23'),
(3, 4, 'some text', '2000-10-25'),
(1, 1, 'stop', '2000-10-25'),
(1, 2, 'oke', '2000-10-30'),
(2, 1, 'waaaw', '2000-10-30');


















