CREATE TABLE IF NOT EXISTS `Bands`(
`band_id` tinyint PRIMARY KEY AUTO_INCREMENT NOT NULL,
`band_name` varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Role_Bands`(
`role_id` smallint NOT NULL,
`band_id` tinyint NOT NULL,
FOREIGN KEY(`role_id`) REFERENCES `Job_Roles`(`role_id`),
FOREIGN KEY(`band_id`) REFERENCES Bands(`band_id`),
PRIMARY KEY(`role_id`, `band_id`)
);