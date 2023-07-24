CREATE TABLE Capabilities (
	capability_id smallint AUTO_INCREMENT NOT NULL PRIMARY KEY,
	name varchar(50) NOT NULL,
	description varchar(100) NOT NULL,
	job_role_id smallint NOT NULL
);

CREATE TABLE Job_Families (
	job_family_id smallint NOT NULL PRIMARY KEY,
	capability_id smallint NOT NULL,
	name varchar(50) NOT NULL
	);

ALTER TABLE Job_Families
ADD CONSTRAINT Fk1_capability_id
FOREIGN KEY (capability_id) references Capabilities(capability_id);

ALTER TABLE Job_Roles
ADD COLUMN job_family_id  smallint;

ALTER TABLE Job_Roles
ADD CONSTRAINT FK1_job_family_id
FOREIGN KEY (job_family_id) REFERENCES Job_Families(job_family_id);
