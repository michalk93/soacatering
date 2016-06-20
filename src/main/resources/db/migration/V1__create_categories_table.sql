CREATE TABLE categories (
  category_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  parent_category_id INT DEFAULT NULL,
  FOREIGN KEY (parent_category_id) REFERENCES categories(category_id)
);