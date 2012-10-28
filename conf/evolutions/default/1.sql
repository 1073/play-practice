# system_user schema

# --- !Ups

CREATE SEQUENCE user_id_seq;
CREATE TABLE system_user (
    id integer NOT NULL DEFAULT nextval('user_id_seq'),
    login_id varchar(255),
    password varchar(255)
    name varchar(255)
);

# --- !Downs

DROP TABLE system_user;
DROP SEQUENCE user_id_seq;