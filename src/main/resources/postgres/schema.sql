CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       fullname VARCHAR(100),
                       email VARCHAR(100),
                       bio TEXT,
                       skills VARCHAR(255)
);

CREATE TABLE projects (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT NOT NULL,
                          title VARCHAR(100) NOT NULL,
                          description TEXT,
                          image_url VARCHAR(255),
                          project_url VARCHAR(255),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);
