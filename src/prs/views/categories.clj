(ns prs.views.categories)

(defn index
  [_req page-vars]
  [:div
   (for [category (:categories page-vars)]
     [:div (:name category)])])
