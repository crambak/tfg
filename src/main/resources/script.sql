CREATE SCHEMA `uoc` ;

use uoc;

drop table `team_rank`;
drop table `statistics_match`;
drop table `ranks`;
drop table `matchs`;
drop table `rating`;
drop table `statistics`;
drop table `team`;

CREATE TABLE `team` (
  `id` int NOT NULL,
  `name` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `abbreviation` varchar(3) NOT NULL,
  `conference` varchar(1) DEFAULT NULL,
  `wins` int DEFAULT '0',
  `loses` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `statistics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `active` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
CREATE TABLE `matchs` (
  `id` int NOT NULL,
  `idawayteam` int NOT NULL,
  `idhometeam` int NOT NULL,
  `date` datetime DEFAULT NULL,
  `season` varchar(20) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `awayteam_idx` (`idawayteam`),
  KEY `hometeam_idx` (`idhometeam`),
  CONSTRAINT `awayteam` FOREIGN KEY (`idawayteam`) REFERENCES `team` (`id`),
  CONSTRAINT `hometeam` FOREIGN KEY (`idhometeam`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `statistics_match` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idmatch` int NOT NULL,
  `idstatistics` int NOT NULL,
  `awayteam` int DEFAULT '0',
  `hometeam` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `match_idx` (`idmatch`),
  KEY `statistics_idx` (`idstatistics`),
  CONSTRAINT `match` FOREIGN KEY (`idmatch`) REFERENCES `matchs` (`id`),
  CONSTRAINT `statistics` FOREIGN KEY (`idstatistics`) REFERENCES `statistics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rating` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idstatistics` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `numbermatchs` int DEFAULT '82',
  `days` int DEFAULT '365',
  `location` varchar(45) DEFAULT 'All',
  `active` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `statistics_idx` (`idstatistics`),
  CONSTRAINT `statistics_rating` FOREIGN KEY (`idstatistics`) REFERENCES `statistics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ranks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idRating` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `min` int DEFAULT '0',
  `max` int DEFAULT '200',
  PRIMARY KEY (`id`),
  KEY `rating_idx` (`idRating`),
  CONSTRAINT `rating` FOREIGN KEY (`idRating`) REFERENCES `rating` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `team_rank` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idteam` int NOT NULL,
  `idrank` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teamrank_idx` (`idteam`),
  KEY `rankteam_idx` (`idrank`),
  CONSTRAINT `rankteam` FOREIGN KEY (`idrank`) REFERENCES `ranks` (`id`),
  CONSTRAINT `teamrank` FOREIGN KEY (`idteam`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;