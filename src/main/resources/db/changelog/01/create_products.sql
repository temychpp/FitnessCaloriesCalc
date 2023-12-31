CREATE TABLE product
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR(255),
    calories      REAL,
    protein       REAL,
    fat           REAL,
    carbohydrates REAL
);

INSERT INTO Product (name, calories, protein, fat, carbohydrates)
VALUES ('Оливье', 95, 7, 4, 7),
       ('Coca-Cola', 42, 0, 0, 10.6),
       ('Огурец', 14, 0.8, 0.3, 1.9);