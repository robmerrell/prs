(ns prs.db.records
  (:require [hugsql.core :as hugsql]
            [clojure.string :as str]))

(hugsql/def-db-fns "sql/records.sql")

(defn value-time->seconds
  "Converts a pr value that is a time (i.e. 23:11) to seconds"
  [value]
  (let [segments (into [] (rseq (str/split value #":")))
        seconds (first segments)
        minutes (second segments)
        hours (get segments 2)]
    (+ (Integer. seconds)
       (if (some? minutes) (* 60 (Integer. minutes)) 0)
       (if (some? hours) (* 3600 (Integer. hours)) 0))))

(defn seconds->value-time
  "Converts seconds to a pr value time"
  [seconds]
  (let [[hours, hours-rem] [(quot seconds 3600) (rem seconds 3600)]
        [minutes, minutes-rem] [(quot hours-rem 60) (rem hours-rem 60)]]
    (str
     (when (> hours 0) (format "%02d:" hours))
     (format "%02d:%02d" minutes minutes-rem))))