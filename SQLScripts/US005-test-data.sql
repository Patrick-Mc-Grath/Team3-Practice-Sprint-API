	INSERT INTO Bands(band_name)
    VALUES ("Trainee");
    
    INSERT INTO Bands(band_name)
    VALUES ("Associate");
    
    INSERT INTO Bands(band_name)
    VALUES ("Senior Associate");
    
    INSERT INTO Bands(band_name)
    VALUES ("Consultant");
    
    INSERT INTO Bands(band_name)
    VALUES ("Manager");
    
    INSERT INTO Competencies(name, description)
    VALUES ("Perspnal Performance", "Effective Leadership require individuals to do stuff");
    
    INSERT INTO Competencies(name, description)
    VALUES ("Working with Others", "Effective Leadership require individuals to get along");
    
    INSERT INTO Competencies(name, description)
    VALUES ("Setting Direction, Development and Accountability", "Effective Leadership require individuals to contribute");
    
    INSERT INTO Competencies(name, description)
    VALUES ("Supporting and Delivering the Strategy", "Effective Leadership require support, contribution, and delivery");
    
    INSERT INTO Competencies(name, description)
    VALUES ("Commerciablity and Risk", "Effective Leadership require individuals to demonstrate effectivness");
    
    INSERT INTO Competencies(name, description)
    VALUES ("Communicating and Influence", "Effective Leadership require individuals to be exceptional");
    
    
    INSERT INTO Band_Competencies(band_id, competency_id)
    VALUES (1, 3);
    
    INSERT INTO Band_Competencies(band_id, competency_id)
    VALUES (2, 4);
    
    INSERT INTO Band_Competencies(band_id, competency_id)
    VALUES (3, 5);
    
    INSERT INTO Band_Competencies(band_id, competency_id)
    VALUES (4, 1);
    
    INSERT INTO Band_Competencies(band_id, competency_id)
    VALUES (5, 2);