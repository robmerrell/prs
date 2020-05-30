-- :name get-by-category-id :? :*
-- :doc Get movements that belong to a category
select * from movements
where category_id = :category-id
order by name

-- :name batch-insert :! :n
-- :doc Insert multiple movements
insert into movements (id, category_id, name, pr_type)
values :tuple*:movements

-- :name remove-all :! :n
delete from movements
