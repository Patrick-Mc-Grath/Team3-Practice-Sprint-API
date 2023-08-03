	INSERT INTO Bands(band_name)
    VALUES ("Trainee"),
    ("Associate"),
    ("Senior Associate"),
    ("Consultant"),
    ("Manager");
    
    INSERT INTO Competencies(name, description)
    VALUES ("Personal Performance", "Effective Leadership require individuals to do stuff"),
    ("Working with Others", "Effective Leadership require individuals to get along"),
    ("Setting Direction, Development and Accountability", "Effective Leadership require individuals to contribute"),
    ("Supporting and Delivering the Strategy", "Effective Leadership require support, contribution, and delivery"),
    ("Commerciablity and Risk", "Effective Leadership require individuals to demonstrate effectivness"),
    ("Communicating and Influence", "Effective Leadership require individuals to be exceptional");

    INSERT INTO Band_Competencies(band_id, competency_id)
    VALUES (1, 3), (2, 4), (3, 5), (4, 1), (5, 2), (1, 1),(1,2),(1,4),(1,5),(1,6);