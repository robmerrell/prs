-- :name get-by-user-and-movement :? :*
-- :doc Get a user's records for a movement
select * from records
where user_id = :user-id
and movement_id = :movement-id
order by created_at desc

-- :name insert :! :n
-- :doc Insert a new record
insert into records (user_id, movement_id, value, created_at)
values (:user-id, :movement-id, :value, :created-at)
