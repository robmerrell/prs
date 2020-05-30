create table users (
  id integer primary key,
  name text,
  email text unique,
  password text
);

create index idx_email_password on users(email, password);
