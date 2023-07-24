CREATE TABLE IF NOT EXISTS Training_Categories (
	training_category_id SMALLINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (training_category_id)
);

ALTER TABLE Training ADD training_category_id SMALLINT NOT NULL;
ALTER TABLE Training ADD FOREIGN KEY (training_category_id) REFERENCES Training_Categories(training_category_id);