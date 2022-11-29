USE mosorinzakharlab1;

DROP PROCEDURE IF EXISTS add_server;
DROP PROCEDURE IF EXISTS add_ten_servers;
DROP TRIGGER IF EXISTS min_6_message_cardinal;
DROP TRIGGER IF EXISTS min_2_server_cardinal;
DROP TRIGGER IF EXISTS max_30_server_cardinal;
DROP TRIGGER IF EXISTS twoZero;
DROP PROCEDURE IF EXISTS findMaxServer;
DROP FUNCTION IF EXISTS maxim;
DROP PROCEDURE IF EXISTS curs;
DROP TRIGGER IF EXISTS hostsInsertTrig;

DROP TABLE IF EXISTS hosts;
CREATE TABLE hosts(
	name VARCHAR(45) NOT NULL,
    server_id int NOT NULL
)ENGINE = INNODB;

DELIMITER //

-- 1
-- 2.c
--
CREATE TRIGGER hostsInsertTrig
BEFORE INSERT
ON hosts FOR EACH ROW
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE nameT VARCHAR(45);
    DECLARE serveridT INT;
    
	DECLARE curs CURSOR 
		FOR SELECT * FROM hosts;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN curs;
    curs_loop : loop
		FETCH curs INTO nameT, serveridT;
        IF done = TRUE THEN LEAVE curs_loop;
        END IF;
        IF (new.name = nameT AND new.server_id = serveridT) THEN
			SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Name and id is allready exists";
		END IF;
	END LOOP;
    CLOSE curs;
END//


-- 2.a
--
--
CREATE PROCEDURE add_server (IN name VARCHAR(45))
BEGIN
	INSERT INTO server VALUES(name);
END//

-- 2.c
--
--
CREATE PROCEDURE add_ten_servers (IN serverName VARCHAR(45))
BEGIN
	DECLARE count int;
    SET count = 1;
    addName: LOOP
		IF count > 10 THEN LEAVE addName;
        END IF;
		INSERT INTO server(name) VALUES(CONCAT(serverName, "-", count));
        SET count = count + 1;
	END LOOP;
END//

-- 2.d
--
--
SET GLOBAL log_bin_trust_function_creators = 1;
CREATE Function maxim()
returns int
Begin
	return (select max(id) from server);
end // 
CREATE PROCEDURE findMaxServer(
	OUT idOut int,
	OUT nameOut int
    )
BEGIN
	SELECT id, name INTO idOut, nameOut
	FROM server WHERE server.id = maxim();
END//



-- 2.e
--
--
create procedure curs()
begin
	declare done int default false;
    declare nameT, timeStampT varChar(40);
    declare some_cursor cursor
		for select name from server;
    declare continue handler for not found 
		set done = true;
	open some_cursor;
    l : loop
		fetch some_cursor into nameT;
        if done = true then leave l;
        end if;
        SET @tmp_query = CONCAT('CREATE TABLE IF NOT EXIST ', name+NOW());
        prepare myquery from @tmp_query;
        execute myquery;
        deallocate prepare myquery;
    end loop;
    close some_cursor;
end //


-- 3.d
--
--
CREATE TRIGGER min_6_message_cardinal
AFTER DELETE
ON message FOR EACH ROW
BEGIN 
	IF (SELECT COUNT(*) FROM message)<6
		THEN SIGNAL SQLSTATE'45000'
		SET MESSAGE_TEXT = 'Delete min cardinal';
	END IF;
END//

-- 3.e
--
--
CREATE TRIGGER min_2_server_cardinal
AFTER DELETE
ON server FOR EACH ROW
BEGIN 
	IF (SELECT COUNT(*) FROM server)<2
		THEN SIGNAL SQLSTATE'45000'
		SET MESSAGE_TEXT = 'Delete min cardinal';
	END IF;
END//

CREATE TRIGGER max_30_server_cardinal
AFTER INSERT
ON server FOR EACH ROW
BEGIN 
	IF (SELECT COUNT(*) FROM server)>30
		THEN SIGNAL SQLSTATE'45000'
		SET MESSAGE_TEXT = 'Insert max cardinal';
	END IF;
END//

-- 3.a
--
--
CREATE TRIGGER twoZero
BEFORE INSERT
ON server FOR EACH ROW
BEGIN 
	IF new.name LIKE '%00'
		THEN SIGNAL SQLSTATE'45000'
		SET MESSAGE_TEXT = 'Insert bad name';
	END IF;
END//



//
DELIMITER ;