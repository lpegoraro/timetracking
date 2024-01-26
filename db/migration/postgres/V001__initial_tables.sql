CREATE TABLE IF NOT EXISTS users (
  id varchar(40),
  username varchar(255) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  email varchar(255) NOT NULL UNIQUE,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_profiles (
  id varchar(40),
  user_id integer NOT NULL UNIQUE,
  first_name varchar(255),
  last_name varchar(255),
  profile_image varchar(255),
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_roles (
  id varchar(40),
  user_id integer NOT NULL UNIQUE user_id,role_id,
  role_id integer NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY user_id (user_id,role_id),

);

CREATE TABLE IF NOT EXISTS roles (
  id varchar(40),
  name varchar(255) NOT NULL UNIQUE,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,
  PRIMARY KEY (id)
);