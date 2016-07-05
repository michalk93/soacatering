CREATE TABLE users (
  user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstname VARCHAR(128) NOT NULL,
  lastname VARCHAR(128) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  is_logged BOOLEAN DEFAULT FALSE
);
INSERT INTO users VALUES (null, "Test", "User", "user@test.mail", "BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps=", FALSE);
INSERT INTO users VALUES (null, "Test", "Manager", "manager@test.mail", "buSkac1OkQU4R/XT/LYdvMkejw7xC+d0jaTEobo4LRc=", FALSE);
INSERT INTO users VALUES (null, "Test", "Admin", "admin@test.mail", "jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=", FALSE);
