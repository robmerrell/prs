(ns prs.db.core
  (:require [mount.core :as mnt]
            [migratus.core :as migratus]
            [prs.config :as config]
            [prs.db.movements :as movements-db]
            [prs.db.categories :as categories-db]))

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

(defn reseed
  "Clean categories and movements and reseed them with data"
  []
  (categories-db/remove-all conn)
  (movements-db/remove-all conn)
  (categories-db/batch-insert conn {:categories [[1 "Bench"]
                                                 [2 "Clean"]
                                                 [3 "Press/Jerk"]
                                                 [4 "Snatch"]
                                                 [5 "Squat"]
                                                 [6 "Cardio"]
                                                 [7 "Hero"]]})
  (movements-db/batch-insert conn {:movements [[1 1 "Standard Grip"]
                                               [2 2 "Clean"]
                                               [3 2 "Clean & Jerk"]
                                               [4 2 "Power Clean"]
                                               [5 2 "Cluster"]
                                               [6 3 "Push Press"]
                                               [7 3 "Push Jerk"]
                                               [8 3 "Split Jerk"]
                                               [9 3 "Shoulder Press"]
                                               [10 4 "Snatch"]
                                               [11 4 "Power Snatch"]
                                               [12 5 "Back Squat"]
                                               [13 5 "Front Squat"]
                                               [14 5 "Overhead Squat"]
                                               [15 6 "5k Row"]
                                               [16 6 "Mile"]
                                               [17 7 "Murph"]
                                               [18 7 "Fran"]
                                               [19 7 "Grace"]
                                               [20 7 "Isabel"]
                                               [21 7 "DT"]]}))

