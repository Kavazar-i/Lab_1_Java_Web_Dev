CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255)       NOT NULL,
    fullname VARCHAR(100),
    email    VARCHAR(100),
    bio      TEXT,
    skills   VARCHAR(255)
);

CREATE TABLE project
(
    id             SERIAL PRIMARY KEY,
    user_id        INTEGER REFERENCES users (id) NOT NULL,
    title          VARCHAR(255)                  NOT NULL,
    description    TEXT,
    project_url    VARCHAR(255),
    photo_filename VARCHAR(255)
);
