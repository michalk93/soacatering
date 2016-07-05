CREATE TABLE subscriptions_courses (
  subscription_course_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  subscription_id INT NOT NULL,
  course_id INT NOT NULL,

  FOREIGN KEY (subscription_id) REFERENCES subscriptions(subscription_id) ON DELETE CASCADE,
  FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);