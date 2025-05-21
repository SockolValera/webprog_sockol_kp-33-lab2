CREATE TABLE IF NOT EXISTS cinema (
                                      id BIGSERIAL PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
                                      address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS session (
                                       id BIGSERIAL PRIMARY KEY,
                                       movie_title VARCHAR(255) NOT NULL,
                                       start_time TIMESTAMP NOT NULL,
                                       cinema_id BIGINT,
                                       FOREIGN KEY (cinema_id) REFERENCES cinema(id)
);

CREATE TABLE IF NOT EXISTS ticket (
                                      id BIGSERIAL PRIMARY KEY,
                                      seat_number VARCHAR(10) NOT NULL,
                                      price DOUBLE PRECISION NOT NULL,
                                      session_id BIGINT,
                                      FOREIGN KEY (session_id) REFERENCES session(id)
);

INSERT INTO cinema (name, address) VALUES
                                       ('Cinema Ukraine', 'st. Khreshchatyk, 22'),
                                       ('Palace of Cinema', 'Victory Avenue, 101');

INSERT INTO session (movie_title, start_time, cinema_id) VALUES
                                                             ('Substance', '2025-05-21 18:30:00', 1),
                                                             ('Conclave', '2025-05-21 20:00:00', 1),
                                                             ('The Last Showgirl', '2025-05-22 17:00:00', 2);
