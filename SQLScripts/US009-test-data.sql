USE Team3_Test_IeuanB;

INSERT INTO Training_Categories(name) VALUES ('Professional Skills');
INSERT INTO Training_Categories(name) VALUES ('Technical Skills');
INSERT INTO Training_Categories(name) VALUES ('Development Programmes');

INSERT INTO Training(name, link, training_category_id) VALUES ('Test professional skills training', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Upcoming-Courses.aspx', 1);
INSERT INTO Training(name, link, training_category_id) VALUES ('Test technical skills training', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Upcoming-Courses.aspx', 2);
INSERT INTO Training(name, link, training_category_id) VALUES ('Test development programme', 'https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Upcoming-Courses.aspx', 3);

INSERT INTO Training_Bands(training_id, band_id) VALUES (1, 0);
INSERT INTO Training_Bands(training_id, band_id) VALUES (1, 1);
INSERT INTO Training_Bands(training_id, band_id) VALUES (2, 0);
INSERT INTO Training_Bands(training_id, band_id) VALUES (3, 0);
INSERT INTO Training_Bands(training_id, band_id) VALUES (3, 1);
INSERT INTO Training_Bands(training_id, band_id) VALUES (3, 2);
INSERT INTO Training_Bands(training_id, band_id) VALUES (3, 3);
INSERT INTO Training_Bands(training_id, band_id) VALUES (3, 4);