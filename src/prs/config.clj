(ns prs.config
  (:require [aero.core :as aero]
            [mount.core :as mnt]
            [clojure.java.io :as io]))

(defn- read-config
  "Read resources/config.edn"
  []
  (println "starting config system")
  (aero/read-config (io/resource "config.edn")
                    {:profile (:profile mnt/args)}))

(mnt/defstate config
  :start (read-config))

(defn database
  "Get the database config"
  []
  (println "getting database config"))

(defn webserver-port
  "Get the port that the webserver should run on"
  []
  (get-in config [:app :server :port]))

