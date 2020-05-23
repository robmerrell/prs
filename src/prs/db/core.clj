(ns prs.db.core
  (:require [mount.core :as mnt]
            [prs.config :as config]))

(defn connect
  []
  (config/database)
  (println "connecting"))

(defn disconnect
  []
  (println "disconnecting"))

(mnt/defstate db
  :start (connect)
  :stop (disconnect))

