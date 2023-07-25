CREATE TABLE Capabilities (
	capability_id smallint AUTO_INCREMENT NOT NULL PRIMARY KEY,
	name varchar(50) NOT NULL,
	description varchar(100) NOT NULL

);

CREATE TABLE Job_Families (
	job_family_id smallint NOT NULL PRIMARY KEY,
	capability_id smallint NOT NULL,
	name varchar(50) NOT NULL

	CONSTRAINT Fk1_capability_id
    FOREIGN KEY (capability_id) references Capabilities(capability_id);
);

ALTER TABLE Job_Roles
ADD COLUMN job_family_id  smallint
ADD CONSTRAINT FK1_job_family_id
FOREIGN KEY (job_family_id) REFERENCES Job_Families(job_family_id);
