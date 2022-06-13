CREATE SCHEMA IF NOT EXISTS feed_me_db;

USE feed_me_db;

# CATEGORY SEEDER
INSERT INTO categories(type)
VALUES ('blank'),
       ('breakfast'),
       ('brunch'),
       ('lunch'),
       ('dinner'),
       ('dessert'),
       ('main course'),
       ('appetizer'),
       ('side dish'),
       ('condiment'),
       ('dip'),
       ('sauce'),
       ('spread'),
       ('snack');