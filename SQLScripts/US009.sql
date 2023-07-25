-- Create Training_Categories table procedure
DELIMITER $$
DROP PROCEDURE IF EXISTS create_training_categories_table $$
CREATE PROCEDURE create_training_categories_table()
BEGIN
    START TRANSACTION;

        CREATE TABLE IF NOT EXISTS Training_Categories (
            training_category_id SMALLINT AUTO_INCREMENT NOT NULL,
            name VARCHAR(100) NOT NULL,
            PRIMARY KEY (training_category_id)
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

-- Create add_training_category_id_to_training_table procedure
DELIMITER $$
DROP PROCEDURE IF EXISTS add_training_category_id_to_training_table $$
CREATE PROCEDURE add_training_category_id_to_training_table()
BEGIN
    START TRANSACTION;

        ALTER TABLE Training ADD training_category_id SMALLINT NOT NULL;
        ALTER TABLE Training ADD FOREIGN KEY (training_category_id) REFERENCES Training_Categories(training_category_id);
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

-- Create create_training_category procedure
DELIMITER $$
DROP PROCEDURE IF EXISTS create_training_category $$
CREATE PROCEDURE create_training_category(
    IN name varchar(100)
)
BEGIN
    START TRANSACTION;

        INSERT INTO Training_Categories(name) VALUES(name);
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

-- Create assign_existing_training_to_category procedure
DELIMITER $$
DROP PROCEDURE IF EXISTS assign_existing_training_to_category $$
CREATE PROCEDURE assign_existing_training_to_category(
    IN training_category_id smallint,
    IN training_id smallint
)
BEGIN
    START TRANSACTION;

        UPDATE Training SET training_category_id = training_category_id WHERE training_id = training_id;
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

--Call create/alter table procedures
CALL create_training_categories_table();
CALL add_training_category_id_to_training_table();

--Call create Training Category procedure
create_training_category('Professional Skills');
create_training_category('Technical Skills');
create_training_category('Development Programmes');

--Call assign training to category procedure
assign_existing_training_to_category(1, 1);
assign_existing_training_to_category(2, 2);
assign_existing_training_to_category(2, 2);