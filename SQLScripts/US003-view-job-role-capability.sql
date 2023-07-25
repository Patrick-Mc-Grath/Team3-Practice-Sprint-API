CREATE TABLE Capabilities (
	capability_id smallint AUTO_INCREMENT NOT NULL PRIMARY KEY,
	name varchar(50) NOT NULL,
	description varchar(100) NOT NULL

);

ALTER TABLE Job_Roles
ADD COLUMN capability_id  smallint;

ALTER TABLE Job_Roles
ADD CONSTRAINT FK1_capability_id
FOREIGN KEY (capability_id) REFERENCES Capabilities(capability_id);
