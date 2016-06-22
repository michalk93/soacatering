CREATE TABLE courses (
  course_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  ingredients TEXT NOT NULL,
  category_id INT NOT NULL,
  price DECIMAL(7,2) NOT NULL,
  FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

INSERT INTO courses VALUES (null, "Bułka z jajkiem i szynką", "jajko, szynka, sałata, masło", 1, 4.99);
INSERT INTO courses VALUES (null, "Bułka z warzywami i szynką", "szynka, pomidor, ogórek, sałata, rzodkiewka, masło", 1, 3.99);
INSERT INTO courses VALUES (null, "Jajecznica z boczkiem", "2 jaja, 100g boczku, chleb", 1, 6.99);
INSERT INTO courses VALUES (null, "Jajecznica z pomidorami", "2 jaja, pomidor, chleb", 1, 5.99);
INSERT INTO courses VALUES (null, "Jajecznica", "2 jaja, chleb", 1, 4.99);
INSERT INTO courses VALUES (null, "Kiełbasa gotowana", "kiełbasa, chleb, ketchup/musztarda", 1, 4.99);
INSERT INTO courses VALUES (null, "Kiełbasa smażona", "kiełbasa, chleb, ketchup/musztarda", 1, 5.99);
INSERT INTO courses VALUES (null, "Kiełbasa smażona z cebulą", "kiełbasa, chleb, cebula, ketchup/musztarda", 1, 6.99);

INSERT INTO courses VALUES (null, "Zupa grzybowa", "150g grzybów borowików", 2, 8.99);
INSERT INTO courses VALUES (null, "Rosół z makaronem", "200g makaronu, 300 ml rosołu", 2, 7.99);
INSERT INTO courses VALUES (null, "Pomidorowa z ryżem", "300 ml pomodorowej, 150g ryżu", 2, 8.99);

INSERT INTO courses VALUES (null, "Schabowy z frytkami i surówką", "200g schabowy, 200g frytek, 150g surówka", 3, 12.99);
INSERT INTO courses VALUES (null, "Pierś z kurczaka z frytkami i surówką", "200g kotlet z piersi kurczaka, 200g frytek, 150g surówka", 3, 15.99);
INSERT INTO courses VALUES (null, "Żeberko w miodzie z ziemniakami i surówką", "250g żeberek, 200g ziemniaków, 150g surówka", 3, 14.99);



