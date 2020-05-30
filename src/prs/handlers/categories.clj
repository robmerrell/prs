(ns prs.handlers.categories
  (:require [prs.layout :refer [render]]
            [prs.views.categories :as views]
            [prs.db.core :as db]
            [prs.db.categories :as categories-db]
            [prs.db.movements :as movements-db]
            [ring.util.response :as response]
            [buddy.auth :as auth]))

(defn index
  "Redirect to the first category"
  [_req]
  (let [first-category (categories-db/get-first db/conn)]
    (response/redirect (str "/categories/" (:id first-category)))))

(defn show
  "Show all movements for a category"
  [req]
  (if-not (auth/authenticated? req)
    (auth/throw-unauthorized)
    (let [categories (categories-db/all db/conn)
          category-id (get-in req [:route-params :id])
          movements (movements-db/get-by-category-id db/conn {:category-id category-id})]
      (render req views/show {:title "Categories" :categories categories :movements movements}))))
