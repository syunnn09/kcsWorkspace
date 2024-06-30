-- --------------------------------------------------------
-- ホスト:                          127.0.0.1
-- サーバーのバージョン:                   11.3.2-MariaDB - mariadb.org binary distribution
-- サーバー OS:                      Win64
-- HeidiSQL バージョン:               12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- workspace のデータベース構造をダンプしています
DROP DATABASE IF EXISTS `workspace`;
CREATE DATABASE IF NOT EXISTS `workspace` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `workspace`;

--  テーブル workspace.bbs の構造をダンプしています
DROP TABLE IF EXISTS `bbs`;
CREATE TABLE IF NOT EXISTS `bbs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `text` text NOT NULL,
  `post_at` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `thread_id` (`thread_id`),
  KEY `userid` (`userid`),
  CONSTRAINT `bbs_ibfk_1` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  CONSTRAINT `bbs_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- テーブル workspace.bbs: ~6 rows (約) のデータをダンプしています
INSERT INTO `bbs` (`id`, `thread_id`, `userid`, `text`, `post_at`) VALUES
	(1, 1, '0001', '楽人なんで~', '2024-06-23 14:54:09'),
	(2, 1, '0001', '西部弱い', '2024-06-24 13:55:35'),
	(5, 1, '0001', '楽人なんで', '2024-06-24 15:05:25'),
	(6, 1, '0001', 'ホークス強すぎる', '2024-06-24 15:05:53'),
	(7, 1, '0002', 'オリックスがんばれ', '2024-06-24 15:06:17'),
	(8, 1, '0001', 'aaa', '2024-06-26 09:23:37');

--  テーブル workspace.department の構造をダンプしています
DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- テーブル workspace.department: ~2 rows (約) のデータをダンプしています
INSERT INTO `department` (`ID`, `NAME`) VALUES
	(1, 'システム開発部'),
	(2, '人事部');

--  テーブル workspace.notification の構造をダンプしています
DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `message` text NOT NULL,
  `isRead` smallint(6) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- テーブル workspace.notification: ~2 rows (約) のデータをダンプしています
INSERT INTO `notification` (`id`, `userid`, `message`, `isRead`) VALUES
	(1, '0001', '新着メッセージがあります', 0),
	(2, '0001', '電話メモがあります', 0);

--  テーブル workspace.schedule の構造をダンプしています
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE IF NOT EXISTS `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `startTime` varchar(9) NOT NULL DEFAULT '9:00',
  `endTime` varchar(9) NOT NULL DEFAULT '15:00',
  `place` varchar(255) DEFAULT NULL,
  `detail` text DEFAULT NULL,
  `isPersonal` smallint(6) DEFAULT 0,
  `isTeam` smallint(6) DEFAULT 0,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- テーブル workspace.schedule: ~7 rows (約) のデータをダンプしています
INSERT INTO `schedule` (`id`, `userid`, `title`, `date`, `startTime`, `endTime`, `place`, `detail`, `isPersonal`, `isTeam`, `status`) VALUES
	(1, '0001', '会議', '2024-06-19', '9:00', '15:00', NULL, NULL, 0, 1, ''),
	(2, '0001', '外出', '2024-06-19', '9:00', '15:00', NULL, NULL, 1, 0, ''),
	(3, '0001', 'テスト', '2024-06-19', '9:00', '15:00', NULL, NULL, 1, 1, ''),
	(4, '0001', '外出', '2024-06-22', '14:30', '15:50', '鹿児島銀行', '', 0, 1, '外出'),
	(5, '0001', '外出', '2024-06-22', '11:30', '15:00', '鹿児島銀行', '', 1, 1, '外出'),
	(6, '0001', '開発', '2024-06-16', '11:40', '12:40', 'KCS', '開発を行います。', 1, 0, '外出'),
	(7, '0001', '開発', '2024-06-10', '10:30', '17:00', 'KCS', '開発を行います。', 0, 1, '外出');

--  テーブル workspace.thread の構造をダンプしています
DROP TABLE IF EXISTS `thread`;
CREATE TABLE IF NOT EXISTS `thread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- テーブル workspace.thread: ~1 rows (約) のデータをダンプしています
INSERT INTO `thread` (`id`, `title`) VALUES
	(1, 'プロ野球');

--  テーブル workspace.users の構造をダンプしています
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `roll` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `departmentId` (`departmentId`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`departmentId`) REFERENCES `department` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- テーブル workspace.users: ~2 rows (約) のデータをダンプしています
INSERT INTO `users` (`userid`, `username`, `PASSWORD`, `departmentId`, `roll`) VALUES
	('0001', '今村', 'kcs', 1, '社長'),
	('0002', '武田', 'kcs', 2, '');

--  テーブル workspace.work の構造をダンプしています
DROP TABLE IF EXISTS `work`;
CREATE TABLE IF NOT EXISTS `work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `DAY` varchar(255) NOT NULL,
  `notices` text DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `work_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- テーブル workspace.work: ~2 rows (約) のデータをダンプしています
INSERT INTO `work` (`id`, `userid`, `DAY`, `notices`, `update_date`) VALUES
	(1, '0001', '2024-06-26', '明日もよろしくお願いします。', '2024-06-26 10:19:33'),
	(11, '0001', '2024-06-27', '', '2024-06-27 11:21:52');

--  テーブル workspace.work_detail の構造をダンプしています
DROP TABLE IF EXISTS `work_detail`;
CREATE TABLE IF NOT EXISTS `work_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `workid` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `START` varchar(9) DEFAULT NULL,
  `END` varchar(9) DEFAULT NULL,
  `detail` text DEFAULT NULL,
  `progress` varchar(50) DEFAULT NULL,
  `remarks` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `workid` (`workid`),
  CONSTRAINT `work_detail_ibfk_1` FOREIGN KEY (`workid`) REFERENCES `work` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- テーブル workspace.work_detail: ~3 rows (約) のデータをダンプしています
INSERT INTO `work_detail` (`id`, `workid`, `num`, `START`, `END`, `detail`, `progress`, `remarks`) VALUES
	(1, 1, 0, '9:00', '15:00', '作業', '完了', ''),
	(2, 1, 1, '', '', NULL, '', ''),
	(4, 11, 0, '9:00', '15:00', '作業', '完了', '');

CREATE TABLE facility(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL
);

CREATE TABLE facility_reserve(
	id INT PRIMARY KEY AUTO_INCREMENT,
	facility_id INT REFERENCES facility(id),
	department_id INT REFERENCES department(ID),
	purpose VARCHAR(255)
);

CREATE TABLE facility_reserve_detail(
	id INT PRIMARY KEY AUTO_INCREMENT,
	reserve_id INT REFERENCES facility_reserve(id),
	`date` DATE NOT NULL,
	`hour` INT NOT NULL
	
);

INSERT INTO facility(`name`) VALUES('102'), ('104'), ('105'), ('106');
INSERT INTO facility_reserve(facility_id, department_id, purpose) VALUES(1, 1, 'チーム会議'), (2, 1, 'チーム会議');
INSERT INTO facility_reserve_detail(reserve_id, `date`, `HOUR`) VALUES(1, '2024/06/14', 15), (1, '2024/06/14', 16), (2, '2024/06/14', 16);
/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
