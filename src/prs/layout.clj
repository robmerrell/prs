(ns prs.layout
  (:require [hiccup.page :as html]
            [buddy.auth :as auth]))

(defn- title
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
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
    [:link {:href "/css/tailwind-base.css" :rel "stylesheet"}]
    [:link {:href "/css/tailwind-components.css" :rel "stylesheet"}]
    [:link {:href "/css/tailwind-utilities.css" :rel "stylesheet"}]
    [:link {:href "/css/app.css" :rel "stylesheet"}]
    [:title (title page-vars)]]
   [:body.bg-gray-dark.mb-12
    [:div (get-in req [:session :user :name])]
    (when (auth/authenticated? req)
      [:div.ml-5.mr-5.mb-10.pt-5.text-right
       [:a.text-green-lighter {:href "#"} (get-in req [:user :name])]])
    ;; flash messages
    [:div.ml-5.mr-5.mt-5
     [:div.bg-gray.shadow-md.rounded
      (when-let [error (get-in req [:flash :error])]
        [:div.mb-4.text-2xl.text-center.text-red error])]]
    [:div.ml-5.mr-5.mt-5 (page-fn req page-vars)]]))

(defn render
  "Renders the app-layout and inner page function"
  [req page-fn page-vars]
  (app-layout req page-fn page-vars))

