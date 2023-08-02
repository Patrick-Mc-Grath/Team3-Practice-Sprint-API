DROP PROCEDURE IF EXISTS createLoginTables;
DELIMITER //

CREATE PROCEDURE createLoginTables()
BEGIN
    CREATE TABLE IF NOT EXISTS `Role` (
    `RoleID` tinyint NOT NULL,
    `Name` varchar(50) NOT NULL,
    PRIMARY KEY (`RoleID`)
    );

    CREATE TABLE IF NOT EXISTS `User` (
    `Username` varchar(50) NOT NULL,
    `Password` varchar(50) NOT NULL,
    `RoleID` tinyint NOT NULL,
    PRIMARY KEY (`Username`),
    FOREIGN KEY (`RoleID`) REFERENCES `Role`(`RoleID`)
    );


    CREATE TABLE IF NOT EXISTS `Token` (
    `Username` varchar(64) NOT NULL,
    `Token` varchar(1000) DEFAULT NULL,
    FOREIGN KEY (`Username`) REFERENCES `User`(`Username`)
    );


END //

DELIMITER ;