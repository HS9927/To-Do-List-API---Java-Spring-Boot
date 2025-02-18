-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for todo_list
CREATE DATABASE IF NOT EXISTS `todo_list` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `todo_list`;

-- Dumping structure for table todo_list.tasks
CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1,
  `is_active` tinyint(4) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `updated_at` varchar(255) DEFAULT NULL,
  `deleted_at` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table todo_list.tasks: ~7 rows (approximately)
REPLACE INTO `tasks` (`id`, `name`, `status`, `is_active`, `created_by`, `updated_by`, `deleted_by`, `created_at`, `updated_at`, `deleted_at`) VALUES
	(1, 'ABC', 1, 1, NULL, NULL, NULL, '2025-02-08 16:07:45', NULL, NULL),
	(2, 'A1', 1, 1, NULL, NULL, NULL, '2025-02-08 16:07:45', NULL, NULL),
	(3, 'config server', 1, 1, 'admin', NULL, NULL, '2025-02-15 13:15:57', NULL, NULL),
	(4, 'config web server', 1, 1, 'admin', NULL, NULL, '2025-02-15 13:15:57', NULL, NULL),
	(5, 'config web server', 1, 1, 'admin', NULL, NULL, '2025-02-15 13:15:57', NULL, NULL),
	(6, 'config web server 6', 1, 0, 'admin', 'admin', 'admin', '2025-02-15 13:15:57', '2025-02-15 13:15:57', '2025-02-15 13:15:57'),
	(7, 'learn docker', 0, 0, 'admin', 'hsok', 'admin', '2025-02-15 13:15:57', '2025-02-15 13:17:23', '2025-02-15 13:18:03');

-- Dumping structure for table todo_list.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table todo_list.users: ~2 rows (approximately)
REPLACE INTO `users` (`id`, `username`, `password`, `role`) VALUES
	(1, 'admin', '$2a$10$TrPPrekjEOX7uTGCYitKJueKP12bt/ekJFMauISn0YpcS07HbzECW', 'ROLE_USER'),
	(2, 'hsok', '$2a$10$/W4Dno4v36J5ZGDj1CWFJOLWMtVyITT/dKLW0hH.gRmCvhRyXslYO', 'ROLE_USER');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
