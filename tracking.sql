-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 26 fév. 2020 à 16:44
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `tracking`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `id_clientmail` int(11) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `adresse` varchar(40) DEFAULT NULL,
  `tel` varchar(40) NOT NULL,
  `carte` varchar(40) DEFAULT NULL,
  `ccv` int(11) DEFAULT NULL,
  `expiry` varchar(40) DEFAULT NULL,
  `premium` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `id_clientmail`, `nom`, `password`, `adresse`, `tel`, `carte`, `ccv`, `expiry`, `premium`) VALUES
(226, 1, 'winfrey', 'winfrey', '1213 Peruque Ridge Dr 63385', '19176976499', '8625520201201255', 275, '07/23', 0),
(229, 18, 'Oussama', '111', 'Thorn Farmhouse, Alyth PH11 8NP', '+441828632112', '8625520201201255', 320, '07/23', 1),
(237, 4, 'Johann Gutenberg ', '000', 'IMM B 13   APPT 5   AL MANSOUR 3 50050', '10000000000', '862552020120125', 275, '07/23', 0),
(243, 17, 'sander00', '111', '1213 Peruque Ridge Dr 63385', '14167050440', '8625520201201255', 275, '12/20', 1),
(245, 23, 'hassan', '000', 'zzz', '0623198124', NULL, NULL, NULL, 0),
(247, 13, 'Hamid Alaoui', '000', '170 E 87th St 10128', '19176976499', '8625520201201255', 320, '12/20', 0),
(249, 24, 'Nietzsche', '000', NULL, '13147240980', NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `clientmail`
--

CREATE TABLE `clientmail` (
  `id_cm` int(11) NOT NULL,
  `id_doc` int(11) NOT NULL,
  `email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clientmail`
--

INSERT INTO `clientmail` (`id_cm`, `id_doc`, `email`) VALUES
(1, 14, 'n14@aachen.com'),
(2, 55, 'n55@aachen.com'),
(4, 35, 'aymenbjuni@gmail.com'),
(13, 101, 'aymanebj@gmail.com'),
(17, 777, 'sander777@fh.aachen'),
(18, 81, 'oussamadridi3290@gmail.com'),
(23, 5000, 'webtrack.client1@gmail.com'),
(24, 8000, 'Nietzsche@godIs.dead'),
(25, 2001, 'homer@simpson.hh');

-- --------------------------------------------------------

--
-- Structure de la table `dossier`
--

CREATE TABLE `dossier` (
  `id_doc` int(11) NOT NULL,
  `nom_cl` varchar(40) NOT NULL,
  `type` varchar(40) NOT NULL,
  `numTacheEnCour` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `dossier`
--

INSERT INTO `dossier` (`id_doc`, `nom_cl`, `type`, `numTacheEnCour`) VALUES
(14, 'winfrey', 'grya', 1),
(35, 'Johann Gutenberg ', 'Acte de mariage', 0),
(51, 'Hamid Alaoui', 'Certificat de ', 1),
(66, 'Benjbara', 'Certificat de ', 1),
(252, 'winfrey', 'pass', 0),
(1998, 'Benjbara', 'Visum', 1),
(2001, 'homer', 'cartoon', 0),
(5000, 'hassan', 'visa', 0),
(8000, 'Nietzsche', 'jahanam', 0);

-- --------------------------------------------------------

--
-- Structure de la table `ended`
--

CREATE TABLE `ended` (
  `id_tache` int(11) NOT NULL,
  `id_respo` int(11) NOT NULL,
  `id_doc` int(11) NOT NULL,
  `valide` tinyint(1) NOT NULL,
  `libelle` varchar(40) NOT NULL,
  `dateDebut` varchar(40) NOT NULL,
  `dateFin` varchar(40) NOT NULL,
  `d_nom_cl` varchar(40) NOT NULL,
  `d_type` varchar(40) NOT NULL,
  `d_numTacheEnCour` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ended`
--

INSERT INTO `ended` (`id_tache`, `id_respo`, `id_doc`, `valide`, `libelle`, `dateDebut`, `dateFin`, `d_nom_cl`, `d_type`, `d_numTacheEnCour`) VALUES
(73, 18, 5000, 1, 'deposition', '2020-02-05', '2020-02-10', 'hassan', 'visa', 0),
(76, 19, 8000, 1, 'erste', '2020-02-01', '2020-02-05', 'Nietzsche', 'jahanam', 1);

-- --------------------------------------------------------

--
-- Structure de la table `messenger`
--

CREATE TABLE `messenger` (
  `id_mess` int(11) NOT NULL,
  `id_doc` int(11) NOT NULL,
  `id_respo` int(11) NOT NULL,
  `message` varchar(400) NOT NULL,
  `attachement` blob DEFAULT NULL,
  `dat` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `messenger`
--

INSERT INTO `messenger` (`id_mess`, `id_doc`, `id_respo`, `message`, `attachement`, `dat`) VALUES
(1, 66, 1, 'bonjour', '', ''),
(2, 81, 1, 'im aymen', NULL, ''),
(3, 55, 2, 'im testing this shit', NULL, ''),
(4, 81, 1, 'duplicate', NULL, ''),
(5, 42, 1, 'Message Supprimé!', NULL, ''),
(6, 42, 2, 'ppppppqqqqqqqqqqqqqqqq', NULL, ''),
(7, 42, 1, 'tessssssst', NULL, ''),
(9, 42, 3, 'last', NULL, ''),
(10, 1, 42, 'bonsoir', NULL, '1:41'),
(11, 1, 42, 'bonsoir', NULL, '1:46'),
(12, 1, 42, 'bonsoir', NULL, '1:46'),
(13, 1, 42, 'bonsoir', NULL, '1:46'),
(14, 4, 42, 'bonsoir', NULL, '00:00'),
(15, 4, 42, 'bonsoir', NULL, '00:00'),
(16, 42, 3, 'Message Supprimé!', NULL, ''),
(17, 1, 42, 'm', NULL, '1:54'),
(18, 42, 1, 'finally', NULL, '2:16'),
(19, 42, 1, 'bonsoir', NULL, '2:16'),
(20, 42, 1, 'hhhh whatsapp fabor', NULL, '2:16'),
(21, 42, 1, 'Message Supprimé!', NULL, '2:21'),
(22, 42, 1, 'Message Supprimé!', NULL, '2:49'),
(23, 42, 1, 'Message Supprimé!', NULL, '2:52'),
(24, 42, 1, 'im robin', NULL, '3:08'),
(25, 42, 1, 'Message Supprimé!', NULL, '3:12'),
(26, 14, 1, 'cc', NULL, '3:15'),
(27, 14, 1, 'anyone here?', NULL, '3:15'),
(28, 14, 1, 'why', NULL, '3:17'),
(29, 14, 1, 'Message Supprimé!', NULL, '3:53'),
(30, 14, 1, 'Message Supprimé!', NULL, '3:54'),
(31, 42, 0, 'Une nouvelle Tache de Dossier 42 est Ajoutée. (Responsable: Aymen) ', NULL, ''),
(32, 42, 0, 'Le Responsable Robin Schulz a Terminé la Tache: 1', NULL, ''),
(33, 42, 11, 'im heerrreee', NULL, '4:01'),
(34, 42, 11, 'Message Supprimé!', NULL, '4:15'),
(35, 42, 0, 'Le Responsable Aymen a Terminé la Tache: 59', NULL, ''),
(36, 42, 0, 'Le Responsable Aymen a fait retourner la tache de Mr.aymenau motif de \'message ola la\'', NULL, ''),
(37, 42, 0, 'Le Responsable Robin Schulz a approuvé la Tache: 1', NULL, ''),
(38, 42, 0, 'Le Responsable Robin Schulz a Terminé la Tache: 1', NULL, ''),
(39, 42, 0, 'Le Responsable Aymen a approuvé la Tache: 59', NULL, ''),
(40, 42, 0, 'Le traitement du Dossier de Mr.Frank von Bauch est Terminé', NULL, ''),
(41, 42, 11, 'hadchi zin', NULL, '4:28'),
(42, 42, 11, 'sba7o', NULL, '18:18'),
(43, 42, 0, 'Le Responsable Aymen a fait retourner la tache de Mr.aymen au motif de \'hakak\'', NULL, ''),
(44, 42, 1, 'l3ez bik alkhsim', NULL, '18:20'),
(45, 42, 1, 'tesyt', NULL, '18:37'),
(46, 42, 1, 'Message Supprimé!', NULL, '18:38'),
(47, 35, 1, 'Message Supprimé!', NULL, '22:05'),
(48, 35, 1, 'mazal.?', NULL, '22:07'),
(49, 35, 5, 'tssna chwya', NULL, '22:08'),
(50, 35, 5, 'Message Supprimé!', NULL, '22:08'),
(51, 35, 5, 'm9loba', NULL, '22:08'),
(52, 35, 5, 'Message Supprimé!', NULL, '22:10'),
(53, 35, 5, 'ola?', NULL, '22:11'),
(54, 35, 5, 'Message Supprimé!', NULL, '22:11'),
(55, 35, 5, 'bonjour', NULL, '22:12'),
(56, 35, 5, 'tsd9 3lih', NULL, '22:13'),
(57, 101, 0, 'Une nouvelle Tache de Dossier 101 est Ajoutée. (Responsable: Patrick Muhammed) ', NULL, ''),
(58, 42, 0, 'Le Responsable Robin Schulz a approuvé la Tache: 1', NULL, ''),
(59, 14, 0, 'Une nouvelle Tache de Dossier 14 est Ajoutée. (Responsable: Hassan Aladin) ', NULL, ''),
(60, 42, 0, 'Le Responsable Robin Schulz a Terminé la Tache: 1', NULL, ''),
(61, 42, 0, 'Le Responsable Aymen a fait retourner la tache de Mr.aymen au motif de \'0\'', NULL, ''),
(62, 42, 0, 'Le Responsable Robin Schulz a approuvé la Tache: 1', NULL, ''),
(63, 101, 0, 'Une nouvelle Tache de Dossier 101 est Ajoutée. (Responsable: abderrahman) ', NULL, ''),
(64, 101, 1, 'bjr', NULL, '3:58'),
(65, 101, 1, 'cv?', NULL, '3:58'),
(66, 101, 1, 'nn', NULL, '3:59'),
(67, 101, 1, 'et toi?', NULL, '3:59'),
(68, 101, 1, 'Message Supprimé!', NULL, '3:59'),
(69, 101, 1, 'hh', NULL, '3:59'),
(70, 101, 0, 'Le Responsable Robin Schulz a Terminé la Tache: 42', NULL, ''),
(71, 42, 0, 'Le Responsable Robin Schulz a Terminé la Tache: 1', NULL, ''),
(72, 42, 0, 'Le Responsable Aymen a approuvé la Tache: 59', NULL, ''),
(73, 101, 5, 'jm3o raskom', NULL, '4:04'),
(74, 101, 0, 'Le Responsable Patrick Muhammed a approuvé la Tache: 60', NULL, ''),
(75, 14, 1, 'Message Supprimé!', NULL, '2:42'),
(76, 14, 0, 'Le Responsable Robin Schulz a Terminé la Tache: 2', NULL, ''),
(77, 14, 0, 'Le Responsable Robin Schulz a Terminé la Tache: 55', NULL, ''),
(78, 22, 1, 'bjr', NULL, '23:45'),
(79, 22, 1, 'Message Supprimé!', NULL, '23:45'),
(80, 252, 0, 'Le dossier Numero 252 est crée', NULL, ''),
(81, 252, 0, 'La premiere Tache de Dossier 252 est Ajoutée. (Responsable: Aymen) ', NULL, ''),
(82, 252, 0, 'Une nouvelle Tache de Dossier 252 est Ajoutée. (Responsable: Aymen) ', NULL, ''),
(83, 252, 0, 'Une nouvelle Tache de Dossier 252 est Ajoutée. (Responsable: abderrahman) ', NULL, ''),
(84, 252, 0, 'Le Responsable Aymen a approuvé la Tache: 63', NULL, ''),
(85, 252, 0, 'Le Responsable Aymen a Terminé la Tache: 63', NULL, ''),
(86, 252, 12, 'ok', NULL, '14:07'),
(87, 252, 0, 'Le Responsable abderrahman a approuvé la Tache: 64', NULL, ''),
(88, 252, 0, 'Le Responsable abderrahman a fait retourner la tache de Mr.winfrey au motif de \'khdma na9sa\'', NULL, ''),
(89, 253, 0, 'Le dossier Numero 253 est crée', NULL, '14:16'),
(90, 253, 0, 'La premiere Tache de Dossier 253 est Ajoutée. (Responsable: De Vinci) ', NULL, '14:16'),
(91, 253, 0, 'Une nouvelle Tache de Dossier 253 est Ajoutée. (Responsable: Aymen) ', NULL, '14:17'),
(92, 253, 11, 'ytob', NULL, '14:19'),
(93, 253, 11, 'Message Supprimé!', NULL, '14:19'),
(94, 99, 0, 'Le Responsable De Vinci a approuvé la Tache: 24', NULL, '14:20'),
(95, 253, 0, 'Le Responsable De Vinci a approuvé la Tache: 65', NULL, '15:06'),
(96, 253, 0, 'Le Responsable De Vinci a Terminé la Tache: 65', NULL, '15:07'),
(97, 252, 0, 'Le Responsable Aymen a Terminé la Tache: 63', NULL, '18:43'),
(98, 252, 0, 'Le Responsable abderrahman a approuvé la Tache: 64', NULL, '18:46'),
(99, 252, 0, 'Le traitement du Dossier de Mr.winfrey est Terminé', NULL, '18:46'),
(100, 555, 0, 'Le dossier Numero 555 est crée', NULL, '5:24'),
(101, 555, 0, 'La premiere Tache de Dossier 555 est Ajoutée. (Responsable: prepa) ', NULL, '5:31'),
(102, 555, 0, 'Une nouvelle Tache de Dossier 555 est Ajoutée. (Responsable: prepa2) ', NULL, '5:31'),
(103, 555, 0, 'Le Responsable prepa a approuvé la Tache: 67', NULL, '5:35'),
(104, 555, 0, 'Le Responsable prepa a Terminé la Tache: 67', NULL, '5:36'),
(105, 555, 0, 'Le Responsable prepa2 a approuvé la Tache: 68', NULL, '5:39'),
(106, 555, 0, 'Le Responsable prepa2 a fait retourner la tache de Mr.Michael snow au motif de \'pas de signature\'', NULL, '5:40'),
(109, 555, 0, 'Le Responsable prepa a approuvé la Tache: 67', NULL, '5:43'),
(110, 555, 0, 'Le Responsable prepa a Terminé la Tache: 67', NULL, '5:43'),
(112, 555, 0, 'Le Responsable prepa2 a approuvé la Tache: 68', NULL, '5:45'),
(113, 555, 0, 'Le traitement du Dossier de Mr.Michael snow est Terminé', NULL, '6:02'),
(114, 2020, 0, 'Le dossier Numero 2020 est crée', NULL, '16:51'),
(115, 2020, 0, 'La premiere Tache de Dossier 2020 est Ajoutée. (Responsable: BachRespo) ', NULL, '16:54'),
(116, 2020, 0, 'Une nouvelle Tache de Dossier 2020 est Ajoutée. (Responsable: Aymen Benjbara) ', NULL, '16:55'),
(117, 2020, 0, 'Le Responsable BachRespo a approuvé la Tache: 69', NULL, '17:00'),
(118, 2020, 0, 'Le Responsable BachRespo a Terminé la Tache: 69', NULL, '17:00'),
(119, 2020, 0, 'Une nouvelle Tache de Dossier 2020 est Ajoutée. (Responsable: Aymen) ', NULL, '17:06'),
(120, 2020, 0, 'Une nouvelle Tache de Dossier 2020 est Ajoutée. (Responsable: edward steward) ', NULL, '17:07'),
(121, 2020, 0, 'Le Responsable Aymen Benjbara a approuvé la Tache: 70', NULL, '17:08'),
(122, 2020, 0, 'Le Responsable Aymen Benjbara a Terminé la Tache: 70', NULL, '17:08'),
(123, 42, 0, 'Le traitement du Dossier de Mr.Frank von Bauch est Terminé', NULL, '17:19'),
(124, 2020, 11, 'Message Supprimé!', NULL, '17:20'),
(125, 2020, 11, 'test', NULL, '17:21'),
(126, 2020, 0, 'Le Responsable Aymen a approuvé la Tache: 71', NULL, '17:21'),
(127, 2020, 0, 'Le Responsable Aymen a Terminé la Tache: 71', NULL, '17:21'),
(128, 2020, 0, 'Le Responsable edward steward a fait retourner la tache de Mr.Bachelor au motif de \'signature manquante\'', NULL, '17:25'),
(129, 5000, 0, 'Le dossier Numero 5000 est crée', NULL, '21:15'),
(130, 5000, 0, 'La premiere Tache de Dossier 5000 est Ajoutée. (Responsable: ali) ', NULL, '21:18'),
(131, 5000, 0, 'Une nouvelle Tache de Dossier 5000 est Ajoutée. (Responsable: edward steward) ', NULL, '21:19'),
(132, 5000, 0, 'Une nouvelle Tache de Dossier 5000 est Ajoutée. (Responsable: Aymen) ', NULL, '21:20'),
(133, 5000, 0, 'Le Responsable ali a approuvé la Tache: 73', NULL, '21:22'),
(134, 5000, 18, 'bonjour', NULL, '21:25'),
(135, 5000, 18, 'Message Supprimé!', NULL, '21:25'),
(136, 5000, 0, 'Le Responsable ali a Terminé la Tache: 73', NULL, '21:25'),
(137, 5000, 9, 'test2', NULL, '21:27'),
(138, 5000, 0, 'Le Responsable edward steward a approuvé la Tache: 74', NULL, '21:28'),
(139, 5000, 0, 'Le Responsable edward steward a fait retourner la tache de Mr.hassan au motif de \'pas de timbre\'', NULL, '21:28'),
(140, 5000, 0, 'Le Responsable ali a approuvé la Tache: 73', NULL, '21:29'),
(141, 5000, 0, 'Le Responsable ali a Terminé la Tache: 73', NULL, '21:30'),
(142, 8000, 0, 'Le dossier Numero 8000 est crée', NULL, '22:32'),
(143, 8000, 0, 'La premiere Tache de Dossier 8000 est Ajoutée. (Responsable: Descart) ', NULL, '22:33'),
(144, 8000, 0, 'Le Responsable Descart a approuvé la Tache: 76', NULL, '22:36'),
(145, 8000, 19, 'Hallo!', NULL, '22:39'),
(146, 8000, 0, 'Le traitement du Dossier de Mr.Nietzsche est Terminé', NULL, '22:52'),
(147, 35, 1, 'Message Supprimé!', NULL, '19:05'),
(148, 2001, 0, ' Der Ordner Nummer 2001 wird erstellt', NULL, '19:14'),
(149, 2001, 0, 'Die erste Aufgabe der Datei 2001 ist hinzugefügt. (Verantwortlich: Robin Schulz) ', NULL, '19:18'),
(150, 2001, 0, 'Die Datei Nummer 2001 hat eine neue Aufgabe. (Verantwortlich: De Vinci) ', NULL, '19:19');

-- --------------------------------------------------------

--
-- Structure de la table `responsable`
--

CREATE TABLE `responsable` (
  `id_respo` int(11) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `photo` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `responsable`
--

INSERT INTO `responsable` (`id_respo`, `nom`, `email`, `password`, `photo`) VALUES
(1, 'Robin Schulz', 'robin.sch@hotmail.fr', 'sugar00', './img/photo1.jpg'),
(2, 'De Vinci', 'Jesus_alber@gmail.com', 'sky11', './img/photo12.jpg'),
(3, 'Dibala Muller', 'Aachen@gmail.com', 'datenbank22', './img/photo33.png'),
(4, 'Hassan Aladin', 'aladin@live.com', 'bisat33', './img/photo4.jpg'),
(5, 'Patrick Muhammed', 'Patrick@gmail.com', 'nofikha44', './img/photo5.png'),
(9, 'edward steward', 'aymenbjuni@gmail.com', '000', './img/photo6.jpg'),
(11, 'Aymen', 'aymanebj@gmail.com', '111', './img/image11.jpg'),
(12, 'abderrahman', 'aymenbjwork@gmail.com', '111', './img/image12.jpg'),
(18, 'ali', 'kargo.gmbh@gmail.com', '000', './img/exam2.jpg'),
(19, 'Descart', 'Descart@nietzsche.IsDead', '000', 'keines');

-- --------------------------------------------------------

--
-- Structure de la table `saved`
--

CREATE TABLE `saved` (
  `id_saved` int(11) NOT NULL,
  `id_doc` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `nom_cl` varchar(40) NOT NULL,
  `type` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `saved`
--

INSERT INTO `saved` (`id_saved`, `id_doc`, `id_client`, `nom_cl`, `type`) VALUES
(7, 14, 226, 'winfrey', 'grya'),
(9, 252, 226, 'winfrey', 'pass'),
(10, 81, 226, 'oussama', 'visa'),
(13, 253, 226, 'winfrey', 'Certificat de naissance'),
(15, 51, 243, 'Hamid Alaoui', 'Certificat de '),
(18, 35, 245, 'Johann Gutenberg ', 'Acte de mariage'),
(20, 66, 237, 'Benjbara', 'Certificat de '),
(22, 1998, 247, 'Benjbara', 'Visum');

-- --------------------------------------------------------

--
-- Structure de la table `sous_tache`
--

CREATE TABLE `sous_tache` (
  `id_sousTache` int(11) NOT NULL,
  `id_tache` int(11) NOT NULL,
  `libelle` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `sous_tache`
--

INSERT INTO `sous_tache` (`id_sousTache`, `id_tache`, `libelle`) VALUES
(1, 2, 'first subtask'),
(2, 1, 'second'),
(3, 1, 'second subtask'),
(4, 1, 'ok'),
(6, 2, '333'),
(7, 1, 'signer'),
(9, 21, 'pas de signature'),
(10, 21, 'pas de signature'),
(11, 20, 'pas de timbre'),
(12, 19, 'pas d\'argent'),
(13, 8, 'kml jee '),
(14, 43, 'bghit'),
(15, 43, 'bghit 3awd hehe'),
(16, 13, '2nd'),
(17, 13, '2nd'),
(18, 13, '1rst'),
(20, 13, 'first subtask'),
(21, 1, 'erst'),
(22, 36, 'oussama'),
(23, 57, 'signer'),
(25, 57, 'photo manquante'),
(26, 1, 'message ola la'),
(28, 1, '0'),
(29, 63, 'khdma na9sa'),
(30, 61, 'lanchon'),
(32, 3, 'test'),
(34, 67, 'pas de signature'),
(35, 3, 'presentation'),
(36, 71, 'signature manquante'),
(37, 73, 'pas de timbre');

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

CREATE TABLE `tache` (
  `id_tache` int(11) NOT NULL,
  `id_respo` int(11) NOT NULL,
  `id_doc` int(11) NOT NULL,
  `valide` tinyint(1) NOT NULL,
  `libelle` varchar(40) NOT NULL,
  `dateDebut` varchar(40) NOT NULL,
  `dateFin` varchar(40) NOT NULL,
  `d_nom_cl` varchar(40) NOT NULL,
  `d_type` varchar(40) NOT NULL,
  `d_numTacheEnCour` int(11) NOT NULL,
  `turn` tinyint(1) NOT NULL,
  `duree` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tache`
--

INSERT INTO `tache` (`id_tache`, `id_respo`, `id_doc`, `valide`, `libelle`, `dateDebut`, `dateFin`, `d_nom_cl`, `d_type`, `d_numTacheEnCour`, `turn`, `duree`) VALUES
(3, 1, 35, 1, 'Imprimer', '2020-01-09', '2020-01-11', 'lounes', 'Certificat de naissance', 0, 0, 2),
(6, 4, 35, 1, 'test', '2020-01-16', '2020-01-18', 'Johann Gutenberg ', 'Acte de mariage', 2, 0, 2),
(7, 5, 35, 1, 'vf', '2020-01-18', '2020-01-21', 'Johann Gutenberg ', 'Acte de mariage', 3, 0, 3),
(8, 9, 35, 1, 'aymen', '2020-01-11', '2020-01-16', 'Johann Gutenberg ', 'Acte de mariage', 1, 0, 5),
(11, 3, 51, 1, 'test', '2020-01-01', '2020-01-08', 'Hamid Alaoui', 'Certificat de travaille', 0, 0, 7),
(12, 3, 51, 0, 'test', '2020-01-23', '2020-01-28', 'Hamid Alaoui', 'Certificat de travaille', 1, 1, 5),
(14, 5, 35, 1, 'bachelor', '2020-01-22', '2020-01-25', 'Johann Gutenberg ', 'Acte de mariage', 5, 0, 3),
(19, 3, 66, 1, 'first', '2020-01-10', '2020-01-13', 'bnali', 'Certificat de marriage', 0, 0, 3),
(20, 1, 66, 1, 'second', '2020-01-14', '2020-01-17', 'bnali', 'Certificat de marriage', 1, 0, 3),
(21, 9, 66, 0, 'before last', '2020-01-18', '2020-01-23', 'bnali', 'Certificat de marriage', 2, 1, 5),
(22, 2, 66, 0, 'last', '2020-01-24', '2020-01-28', 'bnali', 'Certificat de marriage', 0, 0, 4),
(43, 11, 1998, 0, 'FH Aachen', '2020-03-10', '2020-03-13', 'Benjbara', 'Visa', 0, 1, 3),
(44, 12, 1998, 0, 'Botschaft', '2020-03-15', '2020-03-19', 'Benjbara', 'Visa', 0, 0, 4),
(55, 1, 14, 1, 'second subtask', '2020-01-22', '2020-01-24', 'winfrey', 'grya', 0, 1, 22),
(61, 4, 14, 0, 'pitchop', '2020-01-25', '2020-02-01', 'winfrey', 'grya', 2, 0, 5),
(63, 11, 252, 1, 'first', '2020-01-02', '2020-01-09', 'winfrey', 'pass', 0, 0, 7),
(64, 12, 252, 1, 'second', '2020-01-09', '2020-01-16', 'winfrey', 'pass', 2, 0, 5),
(73, 18, 5000, 1, 'deposition', '2020-02-05', '2020-02-10', 'hassan', 'visa', 0, 0, 5),
(74, 9, 5000, 0, 'signature', '2020-02-11', '2020-02-13', 'hassan', 'visa', 1, 1, 2),
(75, 11, 5000, 0, '1rst', '2020-02-22', '2020-02-26', 'hassan', 'visa', 0, 0, 2),
(76, 19, 8000, 1, 'erste', '2020-02-01', '2020-02-05', 'Nietzsche', 'jahanam', 1, 0, 4),
(77, 1, 2001, 0, 'maroc', '2020-02-08', '2020-02-12', 'homer', 'cartoon', 0, 1, 4),
(78, 2, 2001, 0, 'second subtask', '2020-02-12', '2020-02-14', 'homer', 'cartoon', 0, 0, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`),
  ADD UNIQUE KEY `id_clientmail_2` (`id_clientmail`),
  ADD KEY `id_clientmail` (`id_clientmail`);

--
-- Index pour la table `clientmail`
--
ALTER TABLE `clientmail`
  ADD PRIMARY KEY (`id_cm`);

--
-- Index pour la table `dossier`
--
ALTER TABLE `dossier`
  ADD PRIMARY KEY (`id_doc`);

--
-- Index pour la table `ended`
--
ALTER TABLE `ended`
  ADD PRIMARY KEY (`id_tache`);

--
-- Index pour la table `messenger`
--
ALTER TABLE `messenger`
  ADD PRIMARY KEY (`id_mess`);

--
-- Index pour la table `responsable`
--
ALTER TABLE `responsable`
  ADD PRIMARY KEY (`id_respo`);

--
-- Index pour la table `saved`
--
ALTER TABLE `saved`
  ADD PRIMARY KEY (`id_saved`);

--
-- Index pour la table `sous_tache`
--
ALTER TABLE `sous_tache`
  ADD PRIMARY KEY (`id_sousTache`);

--
-- Index pour la table `tache`
--
ALTER TABLE `tache`
  ADD PRIMARY KEY (`id_tache`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=250;

--
-- AUTO_INCREMENT pour la table `clientmail`
--
ALTER TABLE `clientmail`
  MODIFY `id_cm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `dossier`
--
ALTER TABLE `dossier`
  MODIFY `id_doc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8001;

--
-- AUTO_INCREMENT pour la table `ended`
--
ALTER TABLE `ended`
  MODIFY `id_tache` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT pour la table `messenger`
--
ALTER TABLE `messenger`
  MODIFY `id_mess` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=151;

--
-- AUTO_INCREMENT pour la table `responsable`
--
ALTER TABLE `responsable`
  MODIFY `id_respo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `saved`
--
ALTER TABLE `saved`
  MODIFY `id_saved` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `sous_tache`
--
ALTER TABLE `sous_tache`
  MODIFY `id_sousTache` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `tache`
--
ALTER TABLE `tache`
  MODIFY `id_tache` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
