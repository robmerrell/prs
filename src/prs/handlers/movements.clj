(ns prs.handlers.movements
  (:require [prs.layout :refer [render]]
            [prs.db.core :as db]
            [prs.db.movements :as movements-db]
            [prs.db.records :as records-db]
            [prs.views.movements :as views]
            [buddy.auth :as auth]))

(defn show
  "Show the movement and records set for the movement"
  [req]
  (if-not (auth/authenticated? req)
    (auth/throw-unauthorized)
    (let [movement (movements-db/get-by-id db/conn {:id (get-in req [:route-params :id])})
          records (records-db/get-by-user-and-movement db/conn {:movement-id (:id movement) :user-id (get-in req [:user :id])})
          latest (first records)]
      (render req views/show {:title (:name movement) :movement movement :records records :latest latest}))))
