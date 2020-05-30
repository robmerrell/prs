-- :name get-by-category-with-user :? :*
-- :doc Get movements that belong to a category and join the user's latest record
select movements.*, value as pr
from movements
left join records on (
	records.id = (
		select id from records
		where user_id = :user-id
		and movement_id = movements.id
		order by created_at desc
		limit 1
	)
)
where category_id = :category-id
order by name;

-- :name get-by-id :? :1
-- :doc Get a movement by id
select * from movements
where id = :id
limit 1

-- :name batch-insert :! :n
-- :doc Insert multiple movements
insert into movements (id, category_id, name, pr_type)
values :tuple*:movements

-- :name remove-all :! :n
delete from movements
