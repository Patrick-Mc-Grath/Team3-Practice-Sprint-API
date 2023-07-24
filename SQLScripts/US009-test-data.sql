INSERT INTO Training_Categories(name) VALUES ('Professional Skills');
INSERT INTO Training_Categories(name) VALUES ('Technical Skills');
INSERT INTO Training_Categories(name) VALUES ('Development Programmes');

UPDATE Training SET training_category_id = 1 WHERE training_id = 1;
UPDATE Training SET training_category_id = 2 WHERE training_id = 2;
UPDATE Training SET training_category_id = 3 WHERE training_id = 3;