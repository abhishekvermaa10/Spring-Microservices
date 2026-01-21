CREATE DATABASE IF NOT EXISTS petistaan;
USE petistaan;

CREATE TABLE owner_table (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender ENUM ('M','F') NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(10) NOT NULL UNIQUE,
    email_id VARCHAR(255) NOT NULL UNIQUE,
	pet_id INTEGER NOT NULL UNIQUE,
    FOREIGN KEY (pet_id) REFERENCES pet_table(id)
);

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

INSERT INTO owner_table (first_name, last_name, gender, city, state, mobile_number, email_id, pet_id)
VALUES (?,?,?,?,?,?,?,?);

SELECT * 
FROM owner_table ot 
JOIN pet_table pt ON pt.id = ot.pet_id
LEFT JOIN domestic_pet_table dpt ON pt.id = dpt.id
LEFT JOIN wild_pet_table wpt ON pt.id = wpt.id;
