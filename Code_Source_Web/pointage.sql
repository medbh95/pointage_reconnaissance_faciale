-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 12 déc. 2021 à 20:29
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pointage`
--

-- --------------------------------------------------------

--
-- Structure de la table `pointer`
--

CREATE TABLE `pointer` (
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `nom` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `pointer`
--

INSERT INTO `pointer` (`date`, `nom`, `type`) VALUES
('2021-12-12 13:40:59', 'mohamed', 'entree'),
('2021-12-12 13:41:43', 'mohamed', 'sortie'),
('2021-12-12 13:46:47', 'Mohamed', 'entree'),
('2021-12-12 13:57:14', 'Mohamed', 'entree'),
('2021-12-12 14:00:03', 'Mohamed', 'entree'),
('2021-12-12 14:01:43', 'Mohamed', 'sortie'),
('2021-12-12 14:04:22', 'Mohamed', 'entree'),
('2021-12-12 14:41:33', 'Mohamed', 'entree'),
('2021-12-12 14:51:15', 'Unknown', 'entree'),
('2021-12-12 14:54:50', 'Mohamed', 'sortie'),
('2021-12-12 14:55:54', 'Mohamed', 'sortie'),
('2021-12-12 14:57:41', 'Mohamed', 'sortie'),
('2021-12-12 14:59:35', 'Unknown', 'entree'),
('2021-12-12 15:00:29', 'Mohamed', 'entree'),
('2021-12-12 15:15:35', 'Mohamed', 'entree'),
('2021-12-12 15:17:30', 'Mohamed', 'sortie'),
('2021-12-12 15:21:58', 'Mohamed', 'entree'),
('2021-12-12 15:45:03', 'Mohamed', 'sortie'),
('2021-12-12 16:34:12', 'Mohamed', 'entree'),
('2021-12-12 16:34:46', 'Mohamed', 'entree'),
('2021-12-12 16:40:47', 'Mbarka', 'entree'),
('2021-12-12 16:41:38', 'Mbarka', 'sortie'),
('2021-12-12 16:52:31', 'Mohamed', 'entree'),
('2021-12-12 16:53:30', 'Mohamed', 'sortie'),
('2021-12-12 17:03:00', 'Mohamed', 'entree'),
('2021-12-12 18:42:29', 'Mohamed', 'entree'),
('2021-12-12 18:48:23', 'Mohamed', 'sortie'),
('2021-12-12 18:50:02', 'Mohamed', 'entree'),
('2021-12-12 18:54:48', 'Mohamed', 'entree'),
('2021-12-12 19:27:06', 'Mohamed', 'entree'),
('2021-12-12 19:37:36', 'Mohamed', 'sortie'),
('2021-12-12 19:39:26', 'Mohamed', 'entree'),
('2021-12-12 19:40:37', 'Mohamed', 'sortie'),
('2021-12-12 20:02:03', 'Mohamed', 'entree'),
('2021-12-12 20:03:50', 'Mohamed', 'entree');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES
(22, 'ali', '1234', 'ali@email.com'),
(13457779, 'Mohamed', '0000', 'mohamed1995176@gmail.com');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
