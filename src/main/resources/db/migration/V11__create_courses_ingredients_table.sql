CREATE TABLE courses_ingredients (
  course_ingredient_id INT AUTO_INCREMENT NOT NULL,
  ingredient_id INT NOT NULL,
  course_id INT NOT NULL,
  quantity DECIMAL(7,2) DEFAULT 0.0,

  PRIMARY KEY (course_ingredient_id),
  FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id) ON DELETE CASCADE,
  FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);