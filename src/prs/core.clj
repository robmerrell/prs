(ns prs.core
  (:require [mount.core :as mnt]
            [prs.server])
  (:gen-class))

(defn -main
  "Start the application"
  [& args]
  (mnt/start-with-args {:profile :dev}))
