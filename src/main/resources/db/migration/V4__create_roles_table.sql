CREATE TABLE roles (
  role_id INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(64) UNIQUE NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TRIGGER uppercase_insert BEFORE INSERT ON roles FOR EACH ROW SET NEW.name = UPPER(NEW.name);
CREATE TRIGGER uppercase_update BEFORE UPDATE ON roles FOR EACH ROW SET NEW.name = UPPER(NEW.name);

INSERT INTO roles VALUES (null, "manager", "manager@test.mail");
INSERT INTO roles VALUES (null, "user", "user@test.mail");
INSERT INTO roles VALUES (null, "admin", "admin@test.mail");