CREATE TABLE courses_ingredients (
  course_ingredient_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  ingredient_id INT NOT NULL,
  course_id INT NOT NULL,
  quantity DECIMAL(7,2) DEFAULT 0.0,

  FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id) ON DELETE CASCADE,
  FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);