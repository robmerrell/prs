create table movements (
  id integer primary key,
  category_id integer,
  name text
);

create index idx_category_id on users(category_id);
