(ns prs.db.users
  (:require [hugsql.core :as hugsql]
            [prs.db.core :as db]
            [buddy.hashers :as hashers]))

(hugsql/def-db-fns "sql/users.sql")

(defn create
  "creates a new user"
  [user]
  (as-> user ?
      (assoc ? :password (hashers/derive (:password user)))
      (insert db/conn ?)))
