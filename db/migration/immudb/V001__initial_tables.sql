CREATE TABLE IF NOT EXISTS user_salt (
    id varchar(40),
    username varchar(255) NOT NULL UNIQUE,
    salt varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
)