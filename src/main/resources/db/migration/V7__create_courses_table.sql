CREATE TABLE courses (
  course_id INT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  category_id INT NOT NULL,
  price DECIMAL(7,2) NOT NULL,
  image VARCHAR(255) DEFAULT NULL,

  PRIMARY KEY (course_id),
  FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

INSERT INTO courses VALUES (null, "Bułka z jajkiem i szynką", 1, 4.99, NULL);
INSERT INTO courses VALUES (null, "Bułka z warzywami i szynką", 1, 3.99, NULL);
INSERT INTO courses VALUES (null, "Jajecznica z boczkiem", 1, 6.99, NULL);
INSERT INTO courses VALUES (null, "Jajecznica z pomidorami", 1, 5.99, NULL);
INSERT INTO courses VALUES (null, "Jajecznica", 1, 4.99, NULL);
INSERT INTO courses VALUES (null, "Kiełbasa gotowana", 1, 4.99, NULL);
INSERT INTO courses VALUES (null, "Kiełbasa smażona", 1, 5.99, NULL);
INSERT INTO courses VALUES (null, "Kiełbasa smażona z cebulą", 1, 6.99, NULL);

INSERT INTO courses VALUES (null, "Zupa grzybowa", 2, 8.99, NULL);
INSERT INTO courses VALUES (null, "Rosół z makaronem", 2, 7.99, NULL);
INSERT INTO courses VALUES (null, "Pomidorowa z ryżem", 2, 8.99, NULL);

INSERT INTO courses VALUES (null, "Schabowy z frytkami i surówką", 3, 12.99, NULL);
INSERT INTO courses VALUES (null, "Pierś z kurczaka z frytkami i surówką", 3, 15.99, NULL);
INSERT INTO courses VALUES (null, "Żeberko w miodzie z ziemniakami i surówką", 3, 14.99, NULL);



