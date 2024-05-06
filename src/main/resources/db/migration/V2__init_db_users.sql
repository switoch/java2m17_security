CREATE TABLE users (
                         user_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
                         username varchar(45) NOT NULL,
                         password varchar(64) NOT NULL,
                         role varchar(45) NOT NULL,
                         enabled int DEFAULT NULL
);

INSERT INTO users (username,password,role,enabled)
VALUES ('namhm',
        'codejava',
        'ROLE_USER',
        1);

INSERT INTO users (username,password,role,enabled)
VALUES ('admin',
        'nimda',
        'ROLE_ADMIN',
        1);