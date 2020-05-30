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
  (movements-db/batch-insert conn {:movements [[1 1 "Standard Grip" "weight"]
                                               [2 2 "Clean" "weight"]
                                               [3 2 "Clean & Jerk" "weight"]
                                               [4 2 "Power Clean" "weight"]
                                               [5 2 "Cluster" "weight"]
                                               [6 3 "Push Press" "weight"]
                                               [7 3 "Push Jerk" "weight"]
                                               [8 3 "Split Jerk" "weight"]
                                               [9 3 "Shoulder Press" "weight"]
                                               [10 4 "Snatch" "weight"]
                                               [11 4 "Power Snatch" "weight"]
                                               [12 5 "Back Squat" "weight"]
                                               [13 5 "Front Squat" "weight"]
                                               [14 5 "Overhead Squat" "weight"]
                                               [15 6 "5k Row" "time"]
                                               [16 6 "Mile" "time"]
                                               [17 7 "Murph" "time"]
                                               [18 7 "Fran" "time"]
                                               [19 7 "Grace" "time"]
                                               [20 7 "Isabel" "time"]
                                               [21 7 "DT" "time"]]}))
