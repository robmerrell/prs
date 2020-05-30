-- :name get-by-category-id :? :*
-- :doc Get movements that belong to a category
select * from movements
where category_id = :category-id

