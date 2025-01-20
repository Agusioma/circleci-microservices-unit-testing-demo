CREATE DATABASE `springserviceDB`;

CREATE DATABASE `logsDB`

CREATE TABLE `logsDB.Logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `springserviceDB.animal_visits` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `animal_name` varchar(100) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `visit_date` date NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `springserviceDB.veterinarians` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `specialization` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);
