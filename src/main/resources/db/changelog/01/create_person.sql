CREATE TABLE person
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(10),
    created_at TIMESTAMPTZ,
    changed_at TIMESTAMPTZ

);