-- CREATE DATABASE workspace;
USE workspace;

-- DROP TABLE users;
-- DROP TABLE department;
-- DROP TABLE notification;
DROP TABLE SCHEDULE;

CREATE TABLE IF NOT EXISTS department(
	ID INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users(
	userid VARCHAR(255) PRIMARY KEY,
	password VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	departmentId INT REFERENCES department(ID),
	roll VARCHAR(255)
);

-- INSERT INTO department(NAME) VALUES('システム開発部');
-- INSERT INTO users(userid, PASSWORD, username, departmentId, roll) VALUES('0001', 'kcs', '今村', 1, '社長'); 

CREATE TABLE IF NOT EXISTS notification(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userid VARCHAR(255) REFERENCES users(userid),
	message TEXT NOT NULL,
	isRead SMALLINT DEFAULT 0
);

CREATE TABLE SCHEDULE(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userid VARCHAR(255) REFERENCES users(userid),
	title VARCHAR(255),
	status VARCHAR(255),
	date DATE,
	startTime VARCHAR(255),
	endTime VARCHAR(255),
	place VARCHAR(255),
	detail VARCHAR(255),
	isPersonal SMALLINT,
	isTeam SMALLINT
);
