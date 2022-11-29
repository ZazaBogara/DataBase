USE mosorinzakharlab4;

DROP PROCEDURE IF EXISTS insertNewServer;
DROP TRIGGER IF EXISTS checkInsertSameName;


DELIMITER //
CREATE PROCEDURE insertNewServer(
IN nameIn VARCHAR(50)
)
BEGIN
    INSERT INTO server(name) VALUES (nameIn);
END //


CREATE TRIGGER checkInsertSameName
BEFORE INSERT
ON server FOR EACH ROW
BEGIN
    DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE nameT VARCHAR(45);
    DECLARE curs CURSOR
		FOR SELECT name FROM server;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN curs;
    curs_loop : loop
        FETCH curs INTO nameT;
        IF done = TRUE THEN LEAVE curs_loop;
        END IF;
        IF (new.name = nameT) THEN
			SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Name is allready exists";
        END IF;
    END LOOP;
    CLOSE curs;
END;
DELIMITER ;