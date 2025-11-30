CREATE TABLE ships
(
    id   BIGSERIAL PRIMARY KEY,
    uuid UUID         NOT NULL,
    name VARCHAR(150) NOT NULL,

    UNIQUE (uuid),
    UNIQUE (name)
);



CREATE TABLE astronauts
(
    id       BIGSERIAL PRIMARY KEY,
    uuid     UUID         NOT NULL,
    nickname VARCHAR(150) NOT NULL,
    ship_id  BIGINT,

    UNIQUE (uuid),
    UNIQUE (nickname),

    CONSTRAINT fk_astronaut_ship
        FOREIGN KEY (ship_id)
            REFERENCES ships (id)
            ON UPDATE CASCADE
            ON DELETE SET NULL
);


