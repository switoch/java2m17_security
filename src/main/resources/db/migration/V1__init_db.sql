create table note
(
    ID  int PRIMARY KEY,
    title varchar(100) not null unique,
    content varchar(255) CHECK(LENGTH(title)>=3 AND LENGTH(title)<=250)
);