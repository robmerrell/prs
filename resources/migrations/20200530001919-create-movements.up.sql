create table movements (
  id integer primary key,
  category_id integer,
  name text,
  pr_type text not null default 'weight'
);

create index idx_category_id on users(category_id);
