CREATE DATABASE azure ;
\c azure
CREATE TABLE business(id serial PRIMARY KEY , name VARCHAR, owner VARCHAR, email VARCHAR , phone VARCHAR , category VARCHAR , website VARCHAR , creation TIMESTAMP );
CREATE TABLE  signin( id serial PRIMARY KEY , name VARCHAR ,email VARCHAR, phone VARCHAR, password VARCHAR );
CREATE TABLE review( id serial PRIMARY KEY , businessid INT, review VARCHAR );
CREATE TABLE categories(id serial PRIMARY KEY, name VARCHAR);
CREATE DATABASE azure_test WITH TEMPLATE azure ;