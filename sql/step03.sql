-- user는 일반적으로 sql 상 예악어인 경우가 많음. users. **_user
CREATE TABLE users (
    -- PK.
    id VARCHAR(50) PRIMARY KEY, -- UNIQUE, NOT NULL
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL
);

INSERT INTO users (id, name, age)
VALUES ('admin', '관리자', 20);