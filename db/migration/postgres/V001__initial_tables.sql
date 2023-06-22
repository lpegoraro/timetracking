CREATE TABLE IF NOT EXISTS tenant (
    id SERIAL PRIMARY KEY,
    account_name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    created TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "user" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password_salt CHAR(64) NOT NULL,
    salt_hash     CHAR(64) NOT NULL,
    account_id SERIAL REFERENCES tenant(id),
    created TIMESTAMP
);

CREATE TABLE IF NOT EXISTS project (
    id SERIAL PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL UNIQUE,
    created TIMESTAMP,
    account_id SERIAL REFERENCES tenant(id)
);

CREATE TABLE IF NOT EXISTS task (
    id SERIAL PRIMARY KEY,
    project_id SERIAL REFERENCES project(id),
    started TIMESTAMP NOT NULL,
    ended TIMESTAMP,
    state VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS report (
    id SERIAL PRIMARY KEY,
    account_id SERIAL REFERENCES tenant(id),
    url VARCHAR(100) NOT NULL
);
