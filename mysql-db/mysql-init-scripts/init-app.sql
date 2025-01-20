-- Create the user 'taa'
CREATE USER 'taa'@'mysql-db' IDENTIFIED BY '4141';

-- Granting all privileges to user 'taa' on all databases
GRANT ALL PRIVILEGES ON *.* TO 'taa'@'mysql-db' WITH GRANT OPTION;
FLUSH PRIVILEGES;

USE microservicesDB;

CREATE TABLE Logs (
  id INT(11) NOT NULL AUTO_INCREMENT,
  message VARCHAR(255) NOT NULL,
  timestamp DATETIME DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE animal_visits (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  animal_name VARCHAR(100) NOT NULL,
  reason VARCHAR(255) NOT NULL,
  visit_date DATE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE veterinarians (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  specialization VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
