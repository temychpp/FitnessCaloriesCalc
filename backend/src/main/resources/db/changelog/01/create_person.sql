CREATE TABLE person
(
    id                   BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name                 VARCHAR(255) NOT NULL,
    email                VARCHAR(255) NOT NULL,
    password             VARCHAR(255),
    role                 CHAR(10) DEFAULT 'user',
    created_at           TIMESTAMPTZ,
    changed_at           TIMESTAMPTZ,
    gender               CHAR(10),
    age                  INT,
    weight               REAL,
    height               INT,
    activity_coefficient REAL,
    steps_by_day         INT,
    fitness_by_day       INT,
    aerobics_by_day      INT
);

INSERT INTO person(name, email, password, role, gender, age, weight, height,
                   activity_coefficient, steps_by_day, fitness_by_day, aerobics_by_day)
VALUES ('Tem', 'tem@mail.ru', 'tem', 'ADMIN', 'MALE', 35, 99.9, 186, 1.2, 1000, 30, 30),
       ('Ivan', 'ivan@gmail.com', 'ivan', 'USER', 'MALE', 30, 99.9, 190, 1.9, 10000, 20, 20);