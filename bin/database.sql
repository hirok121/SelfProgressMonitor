CREATE DATABASE IF NOT EXISTS academic_progress_tracker;
USE academic_progress_tracker;

CREATE TABLE IF NOT EXISTS signup (
    username VARCHAR(50) NOT NULL,
    roll VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO signup (username, roll, password) VALUES ('testuser', 'roll123', 'testpassword');

SHOW DATABASES;
USE academic_progress_tracker;
SHOW TABLES;
DESCRIBE signup;
SELECT * FROM signup;
