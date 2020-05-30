-- :name get-by-user-and-movement :? :*
-- :doc Get a user's records for a movement
select * from records
where user_id = :user-id
and movement_id = :movement-id
order by created_at desc
