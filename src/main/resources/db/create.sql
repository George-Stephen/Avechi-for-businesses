CREATE DATABASE azure ;
\c azure
CREATE TABLE business(id serial PRIMARY KEY , name VARCHAR, owner VARCHAR, email VARCHAR , phone VARCHAR , category_id
INT
, website VARCHAR , creation TIMESTAMP );
CREATE TABLE  signin( id serial PRIMARY KEY , name VARCHAR ,email VARCHAR, phone VARCHAR, password VARCHAR );
CREATE TABLE review( id serial PRIMARY KEY , businessid INT, review VARCHAR, writtenBy VARCHAR, createdat TIMESTAMP );
CREATE TABLE categories(id serial PRIMARY KEY, name VARCHAR, description VARCHAR);
<<<<<<< HEAD
CREATE TABLE business_users(id serial PRIMARY KEY, user_id INT,business_id INT);
=======
CREATE TABLE signin_review(id serial PRIMARY KEY, signin_id INTEGER, review_id INTEGER);
>>>>>>> 9759878bd7f22aa90a5951ad219d5254d992851f
CREATE DATABASE azure_test WITH TEMPLATE azure ;