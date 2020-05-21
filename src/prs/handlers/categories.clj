(ns prs.handlers.categories
  (:require [prs.layout :refer [render]]
            [prs.views.categories :as views]))

(defn index
  "List all categories"
  [req]
  (let [cats [{:id 1 :name "test"}
              {:id 2 :name "test2"}]]
    (render req views/index {:title "Categories" :categories cats})))
