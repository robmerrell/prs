-- :name get-by-email :? :1
-- :doc Get a user by email
select * from users
where email = :email
 limit 1

-- :name get-by-id :? :1
-- :doc Get a user by id
select * from users
where id = :id
limit 1

-- :name insert :! :n
-- :doc Insert a new user
insert into users (email, name, password)
values (:email, :name, :password)

