-- :name all :? :*
-- :doc Get all categories
select * from categories
order by name

-- :name get-first :? :1
-- :doc Get the first category by name
select * from categories
order by name
limit 1

-- :name insert :! :n
-- :doc Insert a new category
insert into categories (name)
values (:name)

-- :name batch-insert :! :n
-- :doc Insert multiple categories
insert into categories (id, name)
values :tuple*:categories

-- :name remove-all :! :n
delete from categories
