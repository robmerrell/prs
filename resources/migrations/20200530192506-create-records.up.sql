create table records (
  id integer primary key,
  user_id integer,
  movement_id integer,
  value integer,
  created_at integer
);

create index idx_movement_id on records(user_id, movement_id);
