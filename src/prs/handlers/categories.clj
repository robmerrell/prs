(ns prs.handlers.categories
  (:require [prs.layout :refer [render]]
            [prs.views.categories :as views]
            [prs.db.core :as db]
            [prs.db.categories :as categories-db]))

(defn index
  "List all categories"
  [req]
  (let [cats (categories-db/all db/conn)
        movements [{:id 1 :name "Clean and Jerk" :latest "205"}
                   {:id 2 :name "Clean" :latest "215"}
                   {:id 3 :name "Power Clean" :latest "200"}]]
    (render req views/index {:title "Categories" :categories cats :movements movements})))
