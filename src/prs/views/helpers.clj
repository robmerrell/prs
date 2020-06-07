(ns prs.views.helpers
  (:require [prs.db.records :as records-db]))

(defn movement-record-value
  "Returns a formatted value for the given record"
  [pr-type value]
  (if (and (= pr-type "time") value)
    (records-db/seconds->value-time value)
    value))