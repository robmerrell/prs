(ns prs.layout
  (:require [hiccup.page :as html]))

(defn title
  "Generates a title for the title tag"
  [page-vars]
  (if-let [title (:title page-vars)]
    (str "PRs :: " title)
    "PRs"))

(defn app-layout
  "Main app layout"
  [req page-fn page-vars]
  (html/html5
   [:head
    [:title (title page-vars)]]
   [:body
    [:div (page-fn req page-vars)]]))

(defn render
  "Renders the app-layout and inner page function"
  [req page-fn page-vars]
  (app-layout req page-fn page-vars))

