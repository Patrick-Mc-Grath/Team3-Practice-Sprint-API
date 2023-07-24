INSERT INTO Capabilities (name, description, job_role_id) VALUES ("Applied Innovation", "You dont have access", 1);

INSERT INTO Capabilities (name, description, job_role_id) VALUES ("Business Development and Marketing", "Business Test", 2);

INSERT INTO Capabilities (name, description, job_role_id) VALUES ("Engineering", "Test", 2);

UPDATE Job_Roles
SET job_family_id = 2
WHERE role_id = 1;

UPDATE Job_Roles
SET job_family_id = 1
WHERE role_id = 2;