DROP SCHEMA PUBLIC CASCADE;

CREATE CACHED TABLE users (
  id       INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  userName VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL
);

CREATE CACHED TABLE expenses (
  id          INTEGER IDENTITY PRIMARY KEY,
  amount      DOUBLE,
  date        TIMESTAMP,
  description VARCHAR(30),
  accountId   INTEGER NOT NULL
);

ALTER TABLE expenses ADD FOREIGN KEY (accountId) REFERENCES users (id);
