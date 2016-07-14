CREATE TABLE ingredients (
  ingredient_id INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(255) UNIQUE,
  quantity DECIMAL(7,2) DEFAULT 0.0,
  alert_quantity DECIMAL (5,2) DEFAULT 0.0,

  PRIMARY KEY (ingredient_id)
);