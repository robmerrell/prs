(ns prs.db.core
  (:require [mount.core :as mnt]
            [prs.config :as config]))

(defn connect []
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname (config/database-name)})

(defn disconnect
  [db]
  (println "disconnecting from db"))

(mnt/defstate db
  :start (connect)
  :stop (disconnect db))
