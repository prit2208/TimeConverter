CREATE DATABASE timezones_db;

USE timezones_db;

CREATE TABLE countries (
  id INT AUTO_INCREMENT PRIMARY KEY,
  country_name VARCHAR(100),
  time_zone VARCHAR(100)
);

INSERT INTO countries (country_name, time_zone) VALUES
('United States', 'America/New_York'),
('United Kingdom', 'Europe/London'),
('India', 'Asia/Kolkata');
