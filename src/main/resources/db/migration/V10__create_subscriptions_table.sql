CREATE TABLE subscriptions (
  subscription_id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  shipping_address VARCHAR(255) NOT NULL,
  shipping_time TIMESTAMP NOT NULL,
  payment_method VARCHAR(128) NOT NULL,
  weekday SMALLINT NOT NULL,

  PRIMARY KEY (subscription_id),
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
)