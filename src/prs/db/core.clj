(ns prs.db.core
  (:require [mount.core :as mnt]
            [migratus.core :as migratus]
            [prs.config :as config]))

(defn connect []
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname (config/database-name)})

(defn disconnect
  [conn]
  (println "disconnecting from db"))

(mnt/defstate conn
  :start (connect)
  :stop (disconnect conn))

(defn migrate
  "Set up and initialize Migratus. Run migrations from this namespace"
  []
  (-> (config/migrations)
      (assoc :db (connect))
      (migratus/migrate)))

(defn new-migration
  "Create a new migration file"
  [name]
  (migratus/create (config/migrations) name))
