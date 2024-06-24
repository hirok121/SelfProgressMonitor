CREATE DATABASE IF NOT EXISTS academic_progress_tracker;
SHOW DATABASES;
USE academic_progress_tracker;

CREATE TABLE IF NOT EXISTS A2SignUp (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    roll VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);
SHOW TABLES;
select *from A2SignUp; 

