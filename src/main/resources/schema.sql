CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS request (
    request_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    description VARCHAR NOT NULL,
    user_id BIGINT NOT NULL,
    create_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_request_to_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS item (
    item_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    available BOOLEAN NOT NULL,
    user_id BIGINT NOT NULL,
    request_id BIGINT,
    CONSTRAINT fk_item_to_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_item_to_request FOREIGN KEY (request_id) REFERENCES request(request_id)
);

CREATE TABLE IF NOT EXISTS booking (
    booking_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    item_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR NOT NULL,
    CONSTRAINT fr_booking_to_item FOREIGN KEY (item_id) REFERENCES item(item_id),
    CONSTRAINT fr_booking_to_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS comment (
    comment_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    text VARCHAR NOT NULL,
    item_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    time_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fr_comment_to_item FOREIGN KEY (item_id) REFERENCES item(item_id),
    CONSTRAINT fr_comment_to_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);