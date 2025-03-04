-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 04 mars 2025 à 14:19
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `moveyourself`
--

-- --------------------------------------------------------

--
-- Structure de la table `activity`
--

CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `recommended_pathology` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `activity`
--

INSERT INTO `activity` (`id`, `category`, `description`, `name`, `recommended_pathology`) VALUES
(1, 'Flexibility', 'A relaxing activity that improves flexibility and mental health.', 'Yoga', 'Stress'),
(2, 'Cardio', 'A high-intensity cardio activity that improves cardiovascular health.', 'Running', 'Obesity'),
(3, 'Cardio', 'A full-body workout that is easy on the joints.', 'Swimming', 'Arthritis'),
(4, 'Cardio', 'A low-impact cardio activity that strengthens the legs.', 'Cycling', 'Hypertension'),
(5, 'Strength', 'Builds muscle strength and improves bone density.', 'Weight Lifting', 'Osteoporosis'),
(6, 'Flexibility', 'Focuses on core strength, flexibility, and overall body awareness.', 'Pilates', 'Back Pain'),
(7, 'Cardio', 'A fun way to improve cardiovascular health and coordination.', 'Dancing', 'Diabetes'),
(8, 'Cardio', 'Combines cardio with the benefits of being in nature.', 'Hiking', 'Depression'),
(9, 'Flexibility', 'A gentle form of exercise that improves balance and relaxation.', 'Tai Chi', 'Anxiety'),
(10, 'Cardio', 'A high-energy dance workout that burns calories.', 'Zumba', 'Obesity'),
(11, 'Strength', 'Improves cardiovascular health and builds strength.', 'Boxing', 'Stress'),
(12, 'Cardio', 'A full-body workout that improves cardiovascular and muscular endurance.', 'Rowing', 'Hypertension'),
(13, 'Strength', 'Combines cardio and strength training for a full-body workout.', 'Kickboxing', 'Obesity'),
(14, 'Flexibility', 'Improves flexibility and reduces muscle tension.', 'Stretching', 'Arthritis'),
(15, 'Cardio', 'A group exercise class that improves cardiovascular health.', 'Aerobics', 'Diabetes'),
(16, 'Strength', 'A high-intensity workout that combines strength and cardio.', 'CrossFit', 'Obesity'),
(17, 'Strength', 'Improves physical fitness, discipline, and self-defense skills.', 'Martial Arts', 'Stress'),
(18, 'Cardio', 'A low-impact activity that improves cardiovascular health.', 'Walking', 'Hypertension'),
(19, 'Flexibility', 'Reduces stress and improves mental clarity.', 'Meditation', 'Anxiety'),
(20, 'Cardio', 'A high-intensity cardio activity that burns calories quickly.', 'Jump Rope', 'Obesity');

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE `evaluation` (
  `id` bigint(20) NOT NULL,
  `satisfaction_score` int(11) NOT NULL,
  `activity_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id`, `satisfaction_score`, `activity_id`, `user_id`) VALUES
(1, 40, 1, 1),
(2, 90, 1, 2),
(3, 20, NULL, 6),
(4, 20, 7, 6),
(5, 21, 7, 6),
(6, 50, 3, 6),
(7, 60, 7, 6),
(8, 50, 7, 6),
(9, 60, 5, 6),
(10, 70, 19, 8),
(11, 100, 19, 8),
(12, 54, 2, 6);

-- --------------------------------------------------------

--
-- Structure de la table `pathology`
--

CREATE TABLE `pathology` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `pathology`
--

INSERT INTO `pathology` (`id`, `name`) VALUES
(1, 'Diabetes'),
(2, 'Hypertension'),
(3, 'Obesity'),
(4, 'Arthritis'),
(5, 'Back Pain'),
(6, 'Anxiety'),
(7, 'Depression');

-- --------------------------------------------------------

--
-- Structure de la table `program`
--

CREATE TABLE `program` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `rate` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `program`
--

INSERT INTO `program` (`id`, `name`, `user_id`, `rate`) VALUES
(28, 'Week A', 8, 80),
(29, 'ff', 8, NULL),
(30, 'dd', 6, 45);

-- --------------------------------------------------------

--
-- Structure de la table `program_activity`
--

CREATE TABLE `program_activity` (
  `program_id` bigint(20) NOT NULL,
  `activity_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `program_activity`
--

INSERT INTO `program_activity` (`program_id`, `activity_id`) VALUES
(28, 19),
(28, 1),
(30, 15);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pathology` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Structure de la table `user_activity`
--

CREATE TABLE `user_activity` (
  `user_id` bigint(20) NOT NULL,
  `activity_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_activity`
--

INSERT INTO `user_activity` (`user_id`, `activity_id`) VALUES
(4, 11),
(4, 17),
(4, 3),
(4, 16),
(4, 1),
(4, 15);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKndtq224drxo1u9e5hbr58ya3w` (`activity_id`),
  ADD KEY `FKg3adtoslyrqt73xfau3c86ykh` (`user_id`);

--
-- Index pour la table `pathology`
--
ALTER TABLE `pathology`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `program`
--
ALTER TABLE `program`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKda0cnp1fv0puba3w22or6xkpo` (`user_id`);

--
-- Index pour la table `program_activity`
--
ALTER TABLE `program_activity`
  ADD KEY `FK7wya17k0g4j5t0bwsqgcqsqj6` (`activity_id`),
  ADD KEY `FKfmxejs3nfsucpga9bjqy2wi0k` (`program_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user_activity`
--
ALTER TABLE `user_activity`
  ADD KEY `FKlw9o1xb2ki2hnwq1o3kk5dlja` (`activity_id`),
  ADD KEY `FKp78clcyf5okycdv9teohsr2kq` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `activity`
--
ALTER TABLE `activity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `evaluation`
--
ALTER TABLE `evaluation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `pathology`
--
ALTER TABLE `pathology`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `program`
--
ALTER TABLE `program`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `FKg3adtoslyrqt73xfau3c86ykh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKndtq224drxo1u9e5hbr58ya3w` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`);

--
-- Contraintes pour la table `program`
--
ALTER TABLE `program`
  ADD CONSTRAINT `FKda0cnp1fv0puba3w22or6xkpo` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `program_activity`
--
ALTER TABLE `program_activity`
  ADD CONSTRAINT `FK7wya17k0g4j5t0bwsqgcqsqj6` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  ADD CONSTRAINT `FKfmxejs3nfsucpga9bjqy2wi0k` FOREIGN KEY (`program_id`) REFERENCES `program` (`id`);

--
-- Contraintes pour la table `user_activity`
--
ALTER TABLE `user_activity`
  ADD CONSTRAINT `FKlw9o1xb2ki2hnwq1o3kk5dlja` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  ADD CONSTRAINT `FKp78clcyf5okycdv9teohsr2kq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
