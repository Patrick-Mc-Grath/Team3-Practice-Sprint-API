-- Create Training table procedure
DELIMITER $$
DROP PROCEDURE IF EXISTS create_training_table $$
CREATE PROCEDURE create_training_table()
BEGIN
    START TRANSACTION;

        CREATE TABLE IF NOT EXISTS Training (
            training_id SMALLINT AUTO_INCREMENT NOT NULL,
            name VARCHAR(100) NOT NULL,
            link VARCHAR(500) NOT NULL,
            PRIMARY KEY (training_id),
        );
        -- check the number of affected rows
        GET DIAGNOSTICS @rows = ROW_COUNT;
        IF @rows = 0 THEN
            -- If an error occurred, rollback the transaction
            ROLLBACK;
            SELECT 'Transaction rolled back due to error.';
        ELSE
            -- If no error occurred, commit the transaction
            COMMIT;
            SELECT 'Transaction committed successfully.';
        END IF;
END $$

-- Create Training_Bands table procedure
DELIMITER $$
DROP PROCEDURE IF EXISTS create_training_bands_table $$
CREATE PROCEDURE create_training_bands_table()
BEGIN
    START TRANSACTION;

        CREATE TABLE IF NOT EXISTS Training_Bands (
            training_id SMALLINT NOT NULL,
            band_id TINYINT NOT NULL,
            PRIMARY KEY (band_id, training_id),
            FOREIGN KEY(band_id) REFERENCES Bands(band_id),
            FOREIGN KEY(training_id) REFERENCES Training(training_id)
        );
        -- check the number of affected rows
        GET DIAGNOSTICS @rows = ROW_COUNT;
        IF @rows = 0 THEN
            -- If an error occurred, rollback the transaction
            ROLLBACK;
            SELECT 'Transaction rolled back due to error.';
        ELSE
            -- If no error occurred, commit the transaction
            COMMIT;
            SELECT 'Transaction committed successfully.';
        END IF;
END $$

-- Create Training Course procedure
DELIMITER $$
DROP PROCEDURE IF EXISTS create_training_course $$
CREATE PROCEDURE create_training_course(
    IN name varchar(100),
    IN link varchar(500)
)
BEGIN
    START TRANSACTION;

        INSERT INTO training (name, link) VALUES (name, link);
        -- check the number of affected rows
        GET DIAGNOSTICS @rows = ROW_COUNT;
        IF @rows = 0 THEN
            -- If an error occurred, rollback the transaction
            ROLLBACK;
            SELECT 'Transaction rolled back due to error.';
        ELSE
            -- If no error occurred, commit the transaction
            COMMIT;
            SELECT 'Transaction committed successfully.';
        END IF;
END $$

-- Create assign training to band procedure
DELIMITER $$
DROP PROCEDURE IF EXISTS assign_training_to_band $$
CREATE PROCEDURE assign_training_to_band(
    IN training_id smallint,
    IN band_id smallint
)
BEGIN
    START TRANSACTION;

        INSERT INTO Training_Bands(training_id, band_id) VALUES(training_id, band_id);
        -- check the number of affected rows
        GET DIAGNOSTICS @rows = ROW_COUNT;
        IF @rows = 0 THEN
            -- If an error occurred, rollback the transaction
            ROLLBACK;
            SELECT 'Transaction rolled back due to error.';
        ELSE
            -- If no error occurred, commit the transaction
            COMMIT;
            SELECT 'Transaction committed successfully.';
        END IF;
END $$

--Call create table procedures
CALL create_training_categories_table();
CALL create_training_table();
CALL create_training_bands_table();

--Call create training course procedure
CALL create_training_course('Test professional skills training', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Upcoming-Courses.aspx');
CALL create_training_course('Test technical skills training', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Upcoming-Courses.aspx');
CALL create_training_course('Test development programme', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Upcoming-Courses.aspx');

--Call assign training to band procedure
CALL assign_training_to_band(1, 0);
CALL assign_training_to_band(1, 1);
CALL assign_training_to_band(2, 0);
CALL assign_training_to_band(3, 0);
CALL assign_training_to_band(3, 1);
CALL assign_training_to_band(3, 2);
CALL assign_training_to_band(3, 3);
CALL assign_training_to_band(3, 4);
