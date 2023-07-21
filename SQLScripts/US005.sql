USE Team3_Test_IeuanB;

	CREATE TABLE IF NOT EXISTS Competencies (
competency_id TINYINT AUTO_INCREMENT,
name varchar(50),
description varchar (500),
PRIMARY KEY (competency_id)
);

CREATE TABLE IF NOT EXISTS Band_Competencies (
band_competency_id TINYINT AUTO_INCREMENT NOT NULL,
band_id TINYINT NOT NULL,
competency_id TINYINT NOT NULL,
PRIMARY KEY (band_competency_id)
);

ALTER TABLE Band_Competencies
ADD CONSTRAINT fk_Band_Competencies_BandId
FOREIGN KEY(band_id)
REFERENCES Bands(band_id);


ALTER TABLE Band_Competencies
ADD CONSTRAINT fk_Band_Competencies_CompetencyId
FOREIGN KEY(competency_id)
REFERENCES Competencies(competency_id);
