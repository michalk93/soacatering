CREATE TABLE orders (
  order_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  user_id INT NOT NULL,
  created_at TIMESTAMP DEFAULT NOW() NOT NULL,
  shipping_address VARCHAR(512) NOT NULL,
  shipping_time TIMESTAMP NOT NULL,
  status_id INT NOT NULL,
  comment TEXT,

  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (status_id) REFERENCES orders_statuses(status_id)
);