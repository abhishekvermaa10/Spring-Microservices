CREATE DATABASE IF NOT EXISTS petistaan_owner;
USE petistaan_owner;

CREATE TABLE owner_table (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender ENUM ('M','F') NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(10) NOT NULL UNIQUE,
    email_id VARCHAR(255) NOT NULL UNIQUE,
	pet_id INTEGER NOT NULL UNIQUE
);

INSERT INTO owner_table (first_name, last_name, gender, city, state, mobile_number, email_id, pet_id)
VALUES (?,?,?,?,?,?,?,?);

SELECT * 
FROM owner_table ot;
