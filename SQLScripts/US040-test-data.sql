DROP PROCEDURE IF EXISTS createLoginTables;
DELIMITER //

CREATE PROCEDURE createLoginTestData()
BEGIN

INSERT INTO `Role`(RoleID,Name) values (1,'admin');
INSERT INTO `Role`(RoleID,Name) values (2,'user');

INSERT INTO `User`(Username,Password,RoleID) VALUES ('test@kainos.com','password',1);
INSERT INTO `User`(Username,Password,RoleID) VALUES ('test101@kainos.com','password',2);

END //

DELIMITER ;