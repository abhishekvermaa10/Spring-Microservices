CREATE DATABASE IF NOT EXISTS petistaan_pet;
USE petistaan_pet;

CREATE TABLE domestic_pet_table (
	id INTEGER NOT NULL PRIMARY KEY,
	date_of_birth DATE NOT NULL,
    FOREIGN KEY (id) REFERENCES pet_table(id)
);

CREATE TABLE wild_pet_table (
	id INTEGER NOT NULL PRIMARY KEY,
	place_of_birth VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES pet_table(id)
);

CREATE TABLE pet_table (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gender ENUM ('M','F') NOT NULL,
	name VARCHAR(255) NOT NULL,
	type ENUM ('BIRD','CAT','DOG','FISH','RABBIT') NOT NULL
);

INSERT INTO pet_table (name, gender, type)
VALUES (?,?,?);

INSERT INTO domestic_pet_table (id, DATE_of_birth)
VALUES (?,?);

INSERT INTO wild_pet_table (id, place_of_birth)
VALUES (?,?);

SELECT * 
FROM pet_table pt
LEFT JOIN domestic_pet_table dpt ON pt.id = dpt.id
LEFT JOIN wild_pet_table wpt ON pt.id = wpt.id;
