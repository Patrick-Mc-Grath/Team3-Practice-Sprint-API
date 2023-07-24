CREATE TABLE IF NOT EXISTS Training_Categories (
	training_category_id SMALLINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (training_category_id)
);

CREATE TABLE IF NOT EXISTS Training (
	training_id SMALLINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    link VARCHAR(500) NOT NULL,
    training_category_id SMALLINT NOT NULL,
    PRIMARY KEY (training_id),
    FOREIGN KEY (training_category_id) REFERENCES Training_Categories(training_category_id)
);

CREATE TABLE IF NOT EXISTS Training_Bands (
	training_id SMALLINT NOT NULL,
	band_id TINYINT NOT NULL,
    PRIMARY KEY (band_id, training_id),
    FOREIGN KEY(band_id) REFERENCES Bands(band_id),
    FOREIGN KEY(training_id) REFERENCES Training(training_id)
);