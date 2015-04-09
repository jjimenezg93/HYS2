CREATE DATABASE `daaexample`;

CREATE TABLE `daaexample`.`users` (
	`login` varchar(100) NOT NULL,
	`password` varbinary(64) DEFAULT NULL,
	`name` varchar(50) DEFAULT NULL,
	`surname` varchar(100) DEFAULT NULL,
	PRIMARY KEY (`login`)
);

CREATE TABLE `daaexample`.`event`(
	`id` int NOT NULL AUTO_INCREMENT,
	`nameEvent` varchar(50) DEFAULT NULL,
	`dateCreate` timestamp,
	`dateInit` timestamp,
	`dateFinal` timestamp,
	`description` varchar(255) DEFAULT NULL,
	`category` enum('musica','deportes','parranda') DEFAULT 'parranda',
	`image` varchar(400),
	`author` varchar(100),
	PRIMARY KEY (`id`),
	FOREIGN KEY (`author`) REFERENCES users(login)
	
);


CREATE TABLE `daaexample`.`eventUser`(
	`id`int NOT NULL,
	`login` varchar(100) NOT NULL,
	PRIMARY KEY (`id`,`login`),
	FOREIGN KEY (`login`) REFERENCES users(login),
	FOREIGN KEY (`id`) REFERENCES event(id)

);


INSERT INTO `daaexample`.`users` (`login`,`password`,`name`,`surname`) VALUES ('logon','bitch','hiper','bitch');
INSERT INTO `daaexample`.`event` (`nameEvent`,`dateCreate`,`dateInit`,`dateFinal`,`description`,`category`) VALUES ("vamos de parranda","2015-07-15 13:30:00","2015-08-15 13:30:00","2015-08-15 15:30:00","vamos a comer una parrillada","parranda");
INSERT INTO `daaexample`.`event` (`nameEvent`,`dateCreate`,`dateInit`,`dateFinal`,`description`,`category`) VALUES ("club de lectura","2015-06-03 16:30:00","2015-06-03 16:30:00","2015-06-15 18:30:00","hablaremos sobre algun libro","parranda");
INSERT INTO `daaexample`.`eventUser` (`id`,`login`) VALUES (1,'logon');