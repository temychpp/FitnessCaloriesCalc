CREATE TABLE person_product
(
    id
    createdAt TIMESTAMP,

    person_id  BIGINT REFERENCES person (id),
    product_id BIGINT REFERENCES product (id),
    PRIMARY KEY (person_id, product_id)


);