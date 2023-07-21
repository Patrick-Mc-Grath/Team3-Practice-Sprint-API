CREATE TABLE IF NOT EXISTS `Job_Roles` (
                             `role_id` smallint NOT NULL AUTO_INCREMENT,
                             `role_title` varchar(50) NOT NULL,
                             `spec_link` varchar(1000) NOT NULL,
                             `spec_summary` varchar(1000) NOT NULL,
                             PRIMARY KEY (`role_id`)
);
