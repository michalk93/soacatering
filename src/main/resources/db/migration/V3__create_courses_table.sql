CREATE TABLE courses (
  course_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  ingredients TEXT NOT NULL,
  category_id INT NOT NULL,
  price DECIMAL(7,2) NOT NULL,
  FOREIGN KEY (category_id) REFERENCES categories(category_id)
)