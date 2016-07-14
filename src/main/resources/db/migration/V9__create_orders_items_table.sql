CREATE TABLE orders_items (
  order_item_id INT AUTO_INCREMENT NOT NULL,
  order_id INT NOT NULL,
  course_id INT NOT NULL,

  PRIMARY KEY (order_item_id),
  FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
  FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);