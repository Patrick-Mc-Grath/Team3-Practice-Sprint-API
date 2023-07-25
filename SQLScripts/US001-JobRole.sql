

CREATE TABLE IF NOT exists `Job_Roles` (
    `role_id` smallint NOT NULL AUTO_INCREMENT,
    `role_title` varchar(50) NOT NULL,
    `job_family_id` smallint DEFAULT NULL,
    PRIMARY KEY (`role_id`),
    KEY `FK1_job_family_id` (`job_family_id`),
    CONSTRAINT `FK1_job_family_id` FOREIGN KEY (`job_family_id`) REFERENCES `Job_Families` (`job_family_id`)
)