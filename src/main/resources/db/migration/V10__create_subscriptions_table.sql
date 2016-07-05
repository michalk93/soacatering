CREATE TABLE subscriptions (
  subscription_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  shipping_address VARCHAR(255) NOT NULL,
  shipping_time TIMESTAMP NOT NULL,
  weekday SMALLINT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
)