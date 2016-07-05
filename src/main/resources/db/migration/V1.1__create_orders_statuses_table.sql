CREATE TABLE orders_statuses (
  status_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name VARCHAR(128) NOT NULL
);

CREATE TRIGGER lower_insert BEFORE INSERT ON orders_statuses FOR EACH ROW SET NEW.name = LOWER(NEW.name);
CREATE TRIGGER lower_update BEFORE UPDATE ON orders_statuses FOR EACH ROW SET NEW.name = LOWER(NEW.name);

INSERT INTO orders_statuses VALUES (null, "nowe");
INSERT INTO orders_statuses VALUES (null, "w realizacji");
INSERT INTO orders_statuses VALUES (null, "do odbioru");
INSERT INTO orders_statuses VALUES (null, "odebrane");
INSERT INTO orders_statuses VALUES (null, "w dostarczeniu");
INSERT INTO orders_statuses VALUES (null, "dostarczone");
INSERT INTO orders_statuses VALUES (null, "anulowane");
