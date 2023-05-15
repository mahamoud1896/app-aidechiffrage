/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8*/;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

use heroku_765d818e0175979;

DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `id` bigint(20) NOT NULL,
  `action_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `activite`;
CREATE TABLE `activite` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fiche_projet_id` bigint(20) DEFAULT NULL,
  `jours_homme_consommes` double NOT NULL,
  `jours_homme_prevus` double NOT NULL,
  `titre` varchar(20) NOT NULL,
  `type_activite_type_activite_id` bigint(20) NOT NULL,
  `date_debut_activite` datetime(6) DEFAULT NULL,
  `date_fin_activite` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_krjinc3nmw5esed79jdog9uea` (`titre`),
  KEY `FKrapnl3wtcay9o0prn6fg4k24w` (`fiche_projet_id`),
  KEY `FK66d5vs7yjtcgq1h3rt9sconht` (`type_activite_type_activite_id`),
  CONSTRAINT `FK66d5vs7yjtcgq1h3rt9sconht` FOREIGN KEY (`type_activite_type_activite_id`) REFERENCES `type_activite` (`type_activite_id`),
  CONSTRAINT `FKrapnl3wtcay9o0prn6fg4k24w` FOREIGN KEY (`fiche_projet_id`) REFERENCES `fiche_projet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `adresses`;
CREATE TABLE `adresses` (
  `id` bigint(20) NOT NULL,
  `nom_adresse` varchar(50) NOT NULL,
  `pays` varchar(50) NOT NULL,
  `ville` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_a3n8yjv3pem2nt9mde2ieygkn` (`nom_adresse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `nom_client` varchar(50) NOT NULL,
  `adresse_client_id` bigint(20) DEFAULT NULL,
  `pays_client_id` bigint(20) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa54mxfbtm7b88x8lhtjrr13xc` (`adresse_client_id`),
  KEY `FKo8nk3hitshlsi7p6421r3k0gb` (`pays_client_id`),
  KEY `FKbdwc7r7ioqpjf75m7g0mghx20` (`address_id`),
  CONSTRAINT `FKa54mxfbtm7b88x8lhtjrr13xc` FOREIGN KEY (`adresse_client_id`) REFERENCES `adresses` (`id`),
  CONSTRAINT `FKbdwc7r7ioqpjf75m7g0mghx20` FOREIGN KEY (`address_id`) REFERENCES `adresses` (`id`),
  CONSTRAINT `FKo8nk3hitshlsi7p6421r3k0gb` FOREIGN KEY (`pays_client_id`) REFERENCES `pays_client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `depenses_fixes`;
CREATE TABLE `depenses_fixes` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `montant` double NOT NULL,
  `fiche_projet_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoud7l9p6nuoogv8ij94u8udiy` (`fiche_projet_id`),
  CONSTRAINT `FKoud7l9p6nuoogv8ij94u8udiy` FOREIGN KEY (`fiche_projet_id`) REFERENCES `fiche_projet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `fiche_projet`;
CREATE TABLE `fiche_projet` (
  `id` bigint(20) NOT NULL,
  `cout_global` double DEFAULT NULL,
  `date_debut_effective` datetime(6) DEFAULT NULL,
  `date_debut_prev` datetime(6) DEFAULT NULL,
  `date_fin_effective` datetime(6) DEFAULT NULL,
  `date_fin_prev` datetime(6) DEFAULT NULL,
  `desc_synthetique` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `etat_projet` varchar(255) DEFAULT NULL,
  `nom_projet` varchar(20) NOT NULL,
  `type_projet_type_projet_id` bigint(20) NOT NULL,
  `user_user_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `new_client_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t0opls1t04jp846ad9eek6p3s` (`nom_projet`),
  KEY `FKbyht81aios5n6txu79bsifcvk` (`type_projet_type_projet_id`),
  KEY `FK5tffrn7g9b25fq3ytt20dg30h` (`user_user_id`),
  KEY `FKjt2qp7lldy1lwhwy9o9if1wlx` (`client_id`),
  CONSTRAINT `FK5tffrn7g9b25fq3ytt20dg30h` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKbyht81aios5n6txu79bsifcvk` FOREIGN KEY (`type_projet_type_projet_id`) REFERENCES `metier` (`type_projet_id`),
  CONSTRAINT `FKjt2qp7lldy1lwhwy9o9if1wlx` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `metier`;
CREATE TABLE `metier` (
  `type_projet_id` bigint(20) NOT NULL,
  `nom` varchar(60) NOT NULL,
  `offre_id` bigint(20) NOT NULL,
  PRIMARY KEY (`type_projet_id`),
  UNIQUE KEY `UK_np7kd19wsywibwo18qsx1t299` (`nom`),
  KEY `FKmgdhp7gmm2wat9o84lcklmdtb` (`offre_id`),
  CONSTRAINT `FKmgdhp7gmm2wat9o84lcklmdtb` FOREIGN KEY (`offre_id`) REFERENCES `offre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `offre`;
CREATE TABLE `offre` (
  `id` bigint(20) NOT NULL,
  `nom_offre` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5fhq89yughldny6vlgg6bb98a` (`nom_offre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `profil`;
CREATE TABLE `profil` (
  `id` bigint(20) NOT NULL,
  `cout_homme_heure` double NOT NULL,
  `cout_homme_jour` double NOT NULL,
  `fonction` varchar(20) DEFAULT NULL,
  `marge` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p31dlbkb9ww9x4b4pjrttefmx` (`fonction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `ress_activite`;
CREATE TABLE `ress_activite` (
  `id` bigint(20) NOT NULL,
  `duree` bigint(20) DEFAULT NULL,
  `activite_id` bigint(20) NOT NULL,
  `ressource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjmni3bl40t9jc3evgnmjdmfya` (`activite_id`),
  KEY `FKiguyudw9tnljnn9w61se0jhlu` (`ressource_id`),
  CONSTRAINT `FKiguyudw9tnljnn9w61se0jhlu` FOREIGN KEY (`ressource_id`) REFERENCES `ressource` (`id`),
  CONSTRAINT `FKjmni3bl40t9jc3evgnmjdmfya` FOREIGN KEY (`activite_id`) REFERENCES `activite` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `ress_fiche_proj`;
CREATE TABLE `ress_fiche_proj` (
  `fiche_projet_id` bigint(20) NOT NULL,
  `ressource_id` bigint(20) DEFAULT NULL,
  KEY `FKp5xcsmt2o746hv7h3hrvjdeew` (`fiche_projet_id`),
  KEY `FKtfxn2otqj4i9magoc7n7v52bq` (`ressource_id`),
  CONSTRAINT `FKp5xcsmt2o746hv7h3hrvjdeew` FOREIGN KEY (`fiche_projet_id`) REFERENCES `fiche_projet` (`id`),
  CONSTRAINT `FKtfxn2otqj4i9magoc7n7v52bq` FOREIGN KEY (`ressource_id`) REFERENCES `ressource` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fiche_projet_ressource_id_foreign` FOREIGN KEY (`ressource_id`) REFERENCES `ressource` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fkkk` FOREIGN KEY (`ressource_id`) REFERENCES `ressource` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `ressource`;
CREATE TABLE `ressource` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `profil_id` bigint(20) NOT NULL,
  `user_ressource` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7voo7px3gpp1qwv97u7uhwkw2` (`user_ressource`),
  KEY `FK5c6pq42f3coxi8tlghlwravt4` (`profil_id`),
  CONSTRAINT `FK5c6pq42f3coxi8tlghlwravt4` FOREIGN KEY (`profil_id`) REFERENCES `profil` (`id`),
  CONSTRAINT `FKcj6axu3445fruj6gxb9xisnx2` FOREIGN KEY (`user_ressource`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `roles_actions`;
CREATE TABLE `roles_actions` (
  `role_id` bigint(20) NOT NULL,
  `action_id` bigint(20) NOT NULL,
  KEY `FK8exatlwivhh4umfnhkej8el72` (`action_id`),
  KEY `FKpps9f87u0toncruda2ncf1emj` (`role_id`),
  CONSTRAINT `FK8exatlwivhh4umfnhkej8el72` FOREIGN KEY (`action_id`) REFERENCES `action` (`id`),
  CONSTRAINT `FKpps9f87u0toncruda2ncf1emj` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `type_activite`;
CREATE TABLE `type_activite` (
  `type_activite_id` bigint(20) NOT NULL,
  `nom_activite` varchar(20) NOT NULL,
  PRIMARY KEY (`type_activite_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



INSERT INTO `activite` (`id`, `description`, `fiche_projet_id`, `jours_homme_consommes`, `jours_homme_prevus`, `titre`, `type_activite_type_activite_id`, `date_debut_activite`, `date_fin_activite`) VALUES
(29, 'SSS', NULL, 10, 5, 'Designppp', 14, NULL, NULL);
INSERT INTO `activite` (`id`, `description`, `fiche_projet_id`, `jours_homme_consommes`, `jours_homme_prevus`, `titre`, `type_activite_type_activite_id`, `date_debut_activite`, `date_fin_activite`) VALUES
(46, 'FF', NULL, 2, 32, 'DesignPP', 15, NULL, NULL);
INSERT INTO `activite` (`id`, `description`, `fiche_projet_id`, `jours_homme_consommes`, `jours_homme_prevus`, `titre`, `type_activite_type_activite_id`, `date_debut_activite`, `date_fin_activite`) VALUES
(47, 'KKL', 28, 22, 12, 'testt', 15, NULL, NULL);
INSERT INTO `activite` (`id`, `description`, `fiche_projet_id`, `jours_homme_consommes`, `jours_homme_prevus`, `titre`, `type_activite_type_activite_id`, `date_debut_activite`, `date_fin_activite`) VALUES
(50, 'NNNaaaaaa', NULL, 7, 8, 'sdfg', 49, NULL, NULL),
(92, 'juste un test', NULL, 8, 5, 'deploiement', 14, NULL, NULL),
(93, 'DDDD', NULL, 1, 3, 'aze', 14, NULL, NULL),
(112, 'Phase de conception', 111, 2, 2, 'conception', 15, NULL, NULL),
(117, 'juste un test', 41, 5, 4, 'Testt02', 15, NULL, NULL),
(125, 'juste un test', 88, 3, 2, 'SS', 14, '2023-01-12 00:00:00.000000', '2023-01-14 00:00:00.000000'),
(131, 'juste un test', 127, 3, 2, 'kk', 49, '2023-01-25 00:00:00.000000', '2023-01-27 00:00:00.000000'),
(135, 'design interface admin', 127, 1, 3, 'Design', 14, '2023-01-31 00:00:00.000000', '2023-02-01 00:00:00.000000');

INSERT INTO `adresses` (`id`, `nom_adresse`, `pays`, `ville`) VALUES
(86, 'cite sonatel', 'Senegal', 'Dakar');
INSERT INTO `adresses` (`id`, `nom_adresse`, `pays`, `ville`) VALUES
(90, 'Fann, Colobane', 'Senegal', 'Dakar');
INSERT INTO `adresses` (`id`, `nom_adresse`, `pays`, `ville`) VALUES
(91, 'Colobane', 'Senegal', 'Dakar');
INSERT INTO `adresses` (`id`, `nom_adresse`, `pays`, `ville`) VALUES
(122, 'Fass', 'Senegal', 'Dakar');

INSERT INTO `client` (`id`, `nom_client`, `adresse_client_id`, `pays_client_id`, `address_id`) VALUES
(85, 'Orange', NULL, NULL, 86);
INSERT INTO `client` (`id`, `nom_client`, `adresse_client_id`, `pays_client_id`, `address_id`) VALUES
(89, 'OMVS', NULL, NULL, 91);
INSERT INTO `client` (`id`, `nom_client`, `adresse_client_id`, `pays_client_id`, `address_id`) VALUES
(121, 'ORANGEE', NULL, NULL, 122);

INSERT INTO `depenses_fixes` (`id`, `libelle`, `montant`, `fiche_projet_id`) VALUES
(31, 'Loyer', 150000, 28);
INSERT INTO `depenses_fixes` (`id`, `libelle`, `montant`, `fiche_projet_id`) VALUES
(52, 'Abont Internet', 15999, 38);
INSERT INTO `depenses_fixes` (`id`, `libelle`, `montant`, `fiche_projet_id`) VALUES
(114, 'ss', 200, 38);

INSERT INTO `fiche_projet` (`id`, `cout_global`, `date_debut_effective`, `date_debut_prev`, `date_fin_effective`, `date_fin_prev`, `desc_synthetique`, `description`, `etat_projet`, `nom_projet`, `type_projet_type_projet_id`, `user_user_id`, `client_id`, `new_client_name`) VALUES
(28, 20000, '2022-12-23 00:00:00.000000', '2022-12-22 00:00:00.000000', '2022-12-31 00:00:00.000000', '2022-12-24 00:00:00.000000', 'SS', 'juste un test', 'CLOS', 'TestPPP', 11, NULL, 85, NULL);
INSERT INTO `fiche_projet` (`id`, `cout_global`, `date_debut_effective`, `date_debut_prev`, `date_fin_effective`, `date_fin_prev`, `desc_synthetique`, `description`, `etat_projet`, `nom_projet`, `type_projet_type_projet_id`, `user_user_id`, `client_id`, `new_client_name`) VALUES
(38, 500, '2022-12-30 00:00:00.000000', '2022-12-23 00:00:00.000000', '2023-01-08 00:00:00.000000', '2022-12-28 00:00:00.000000', 'VFDBD', 'jdjj', 'CLOS', 'Scoring TPE', 12, NULL, 85, NULL);
INSERT INTO `fiche_projet` (`id`, `cout_global`, `date_debut_effective`, `date_debut_prev`, `date_fin_effective`, `date_fin_prev`, `desc_synthetique`, `description`, `etat_projet`, `nom_projet`, `type_projet_type_projet_id`, `user_user_id`, `client_id`, `new_client_name`) VALUES
(39, 5000, '2022-12-23 00:00:00.000000', '2022-12-22 00:00:00.000000', '2022-12-31 00:00:00.000000', '2022-12-24 00:00:00.000000', 'SS', 'juste un test', 'EN_COURS', 'Testsss', 11, NULL, 85, NULL);
INSERT INTO `fiche_projet` (`id`, `cout_global`, `date_debut_effective`, `date_debut_prev`, `date_fin_effective`, `date_fin_prev`, `desc_synthetique`, `description`, `etat_projet`, `nom_projet`, `type_projet_type_projet_id`, `user_user_id`, `client_id`, `new_client_name`) VALUES
(41, 200, '2022-12-23 00:00:00.000000', '2022-12-22 00:00:00.000000', '2022-12-31 00:00:00.000000', '2022-12-24 00:00:00.000000', 'SS', 'juste un test', 'EN_COURS', 'Testffff', 12, NULL, 85, NULL),
(42, 200, '2022-12-23 00:00:00.000000', '2022-12-22 00:00:00.000000', '2022-12-31 00:00:00.000000', '2022-12-24 00:00:00.000000', 'SS', 'juste un test', 'EN_COURS', 'Testaaa', 11, NULL, 85, NULL),
(43, 20000, '2022-12-23 00:00:00.000000', '2022-12-22 00:00:00.000000', '2022-12-31 00:00:00.000000', '2022-12-24 00:00:00.000000', 'SS', 'juste un test', 'EN_COURS', 'Testrrr', 11, NULL, 85, NULL),
(44, 2000, '2022-12-23 00:00:00.000000', '2022-12-22 00:00:00.000000', '2022-12-31 00:00:00.000000', '2022-12-24 00:00:00.000000', 'SS', 'juste un test', 'EN_COURS', 'Testww', 12, NULL, 85, NULL),
(45, 200, '2022-12-23 00:00:00.000000', '2022-12-22 00:00:00.000000', '2022-12-31 00:00:00.000000', '2022-12-24 00:00:00.000000', 'SS', 'juste un test', 'EN_COURS', 'TestHHH', 11, NULL, 85, NULL),
(88, 50000, '2023-01-08 00:00:00.000000', '2023-01-05 00:00:00.000000', '2023-01-20 00:00:00.000000', '2023-01-13 00:00:00.000000', 'DDDD', 'juste un test', 'EN_COURS', 'gestion incident', 87, NULL, 85, NULL),
(111, 40000, '2023-01-13 00:00:00.000000', '2023-01-10 00:00:00.000000', '2023-01-24 00:00:00.000000', '2023-01-21 00:00:00.000000', 'Ceci est un test', 'Ce projet a pour but de gérer les rdv clients médecins', 'EN_COURS', 'gestion rdv', 107, NULL, 89, NULL),
(123, 20000, '2023-01-21 00:00:00.000000', '2023-01-11 00:00:00.000000', '2023-01-29 00:00:00.000000', '2023-01-20 00:00:00.000000', '', 'ceeci est un test', 'EN_COURS', 'gestion rdvv', 11, NULL, 85, NULL),
(127, 35000, '2023-01-15 00:00:00.000000', '2023-01-12 00:00:00.000000', '2023-01-26 00:00:00.000000', '2023-01-21 00:00:00.000000', 'ceci est la description', 'besoin client', 'LIVRE', 'dd', 107, NULL, 85, NULL),
(128, 3000, '2023-01-22 00:00:00.000000', '2023-01-20 00:00:00.000000', '2023-02-03 00:00:00.000000', '2023-01-28 00:00:00.000000', NULL, 'Ceci est un testModalForm', 'EN_COURS', 'TestModalForm', 107, NULL, 89, NULL),
(132, 33, '2023-01-21 00:00:00.000000', '2023-01-19 00:00:00.000000', '2023-02-02 00:00:00.000000', '2023-01-27 00:00:00.000000', NULL, 'lzs besoins', 'EN_COURS', 'll', 11, NULL, 89, NULL),
(137, 2, '2023-01-27 00:00:00.000000', '2023-01-19 00:00:00.000000', '2023-01-12 00:00:00.000000', '2023-01-03 00:00:00.000000', NULL, 'z', 'LIVRE', 'ddQD', 12, NULL, 89, NULL);

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(138);


INSERT INTO `metier` (`type_projet_id`, `nom`, `offre_id`) VALUES
(11, 'DEV FROM SCRATCH', 7);
INSERT INTO `metier` (`type_projet_id`, `nom`, `offre_id`) VALUES
(12, 'Formation Apex', 9);
INSERT INTO `metier` (`type_projet_id`, `nom`, `offre_id`) VALUES
(87, 'Refonte', 10);
INSERT INTO `metier` (`type_projet_id`, `nom`, `offre_id`) VALUES
(107, 'IA', 8);

INSERT INTO `offre` (`id`, `nom_offre`) VALUES
(8, 'BI-ANALYTICS');
INSERT INTO `offre` (`id`, `nom_offre`) VALUES
(10, 'CONSEILS');
INSERT INTO `offre` (`id`, `nom_offre`) VALUES
(7, 'Developpement');
INSERT INTO `offre` (`id`, `nom_offre`) VALUES
(9, 'FORMATION');

INSERT INTO `profil` (`id`, `cout_homme_heure`, `cout_homme_jour`, `fonction`, `marge`) VALUES
(16, 10000, 80000, 'Web Dev1', 30);
INSERT INTO `profil` (`id`, `cout_homme_heure`, `cout_homme_jour`, `fonction`, `marge`) VALUES
(17, 15000, 120000, 'Web Dev2', 30);
INSERT INTO `profil` (`id`, `cout_homme_heure`, `cout_homme_jour`, `fonction`, `marge`) VALUES
(18, 15000, 120000, 'Testeur', 30);
INSERT INTO `profil` (`id`, `cout_homme_heure`, `cout_homme_jour`, `fonction`, `marge`) VALUES
(19, 20000, 160000, 'Analyst', 30),
(20, 15000, 120000, 'Designer 1', 30),
(21, 25000, 200000, 'Expert', 37),
(110, 500000, 330000, 'DEVOPS', 20);

INSERT INTO `ress_activite` (`id`, `duree`, `activite_id`, `ressource_id`) VALUES
(113, 2, 112, 27);
INSERT INTO `ress_activite` (`id`, `duree`, `activite_id`, `ressource_id`) VALUES
(118, 4, 117, 76);
INSERT INTO `ress_activite` (`id`, `duree`, `activite_id`, `ressource_id`) VALUES
(126, 4, 125, 74);

INSERT INTO `ress_fiche_proj` (`fiche_projet_id`, `ressource_id`) VALUES
(88, 74);
INSERT INTO `ress_fiche_proj` (`fiche_projet_id`, `ressource_id`) VALUES
(111, 27);
INSERT INTO `ress_fiche_proj` (`fiche_projet_id`, `ressource_id`) VALUES
(111, 79);
INSERT INTO `ress_fiche_proj` (`fiche_projet_id`, `ressource_id`) VALUES
(41, 74),
(41, 76),
(123, 27),
(123, 74),
(128, 27),
(128, 74),
(132, 97),
(127, 27),
(127, 58),
(137, 102);

INSERT INTO `ressource` (`id`, `nom`, `prenom`, `profil_id`, `user_ressource`) VALUES
(27, 'Ndiaye', 'Cheick', 21, NULL);
INSERT INTO `ressource` (`id`, `nom`, `prenom`, `profil_id`, `user_ressource`) VALUES
(58, 'e1902', 'momotour', 16, NULL);
INSERT INTO `ressource` (`id`, `nom`, `prenom`, `profil_id`, `user_ressource`) VALUES
(74, 'toure', 'nina', 18, NULL);
INSERT INTO `ressource` (`id`, `nom`, `prenom`, `profil_id`, `user_ressource`) VALUES
(76, 'Diakite', 'Hamo', 21, NULL),
(79, 'toure', 'mahamoud', 16, NULL),
(81, 'emomo', 'momotour', 16, NULL),
(97, 'toure', 'mahamoud', 21, NULL),
(100, 'Adja', 'adjja', 16, NULL),
(102, 'Adja', 'adjja', 19, NULL),
(133, 'toure', 'mahamoud', 16, NULL);

INSERT INTO `role` (`id`, `role_name`) VALUES
(3, 'ADMIN');
INSERT INTO `role` (`id`, `role_name`) VALUES
(4, 'COMMERCIAL');
INSERT INTO `role` (`id`, `role_name`) VALUES
(5, 'DG');
INSERT INTO `role` (`id`, `role_name`) VALUES
(6, 'TECHNIQUE');



INSERT INTO `type_activite` (`type_activite_id`, `nom_activite`) VALUES
(14, 'Test');
INSERT INTO `type_activite` (`type_activite_id`, `nom_activite`) VALUES
(15, 'Conception');
INSERT INTO `type_activite` (`type_activite_id`, `nom_activite`) VALUES
(49, 'Dev');
INSERT INTO `type_activite` (`type_activite_id`, `nom_activite`) VALUES
(109, 'Déploiement');

INSERT INTO `user` (`user_id`, `active`, `password`, `provider`, `username`) VALUES
(34, 1, NULL, NULL, 'mahamoud.toure@aristarc-conseils.com');
INSERT INTO `user` (`user_id`, `active`, `password`, `provider`, `username`) VALUES
(57, 1, NULL, 'GOOGLE', 'momotoure1902@gmail.com');
INSERT INTO `user` (`user_id`, `active`, `password`, `provider`, `username`) VALUES
(82, 1, NULL, 'GOOGLE', 'momotouremomo96@gmail.com');
INSERT INTO `user` (`user_id`, `active`, `password`, `provider`, `username`) VALUES
(134, 1, NULL, 'GOOGLE', 'mahamoudtoure76@gmail.com');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(34, 5);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(57, 6);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;