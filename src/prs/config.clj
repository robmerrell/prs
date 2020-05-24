(ns prs.config
  (:require [aero.core :as aero]
            [mount.core :as mnt]
            [clojure.java.io :as io]))

(defn- read-config
  "Read and parse resources/config.edn"
  []
  (aero/read-config (io/resource "config.edn")
                    {:profile (:profile (mnt/args))}))

(mnt/defstate config
  :start (read-config))

(defn database-name []
  (get-in config [:app :database :name]))

(defn webserver-port []
  (get-in config [:app :server :port]))

(defn migrations []
  (get-in config [:app :migrations]))
