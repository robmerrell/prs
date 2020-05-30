-- :name all :? :*
-- :doc Get all categories
select * from categories
order by name

-- :name get-first :? :1
-- :doc Get the first category by name
select * from categories
order by name
limit 1
