(ns prs.core
  (:require [compojure.core :refer [defroutes GET]]
            [ring.adapter.jetty :as ring]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [prs.handlers.categories :as categories])
  (:gen-class))

(defroutes routes
  (GET "/" [] "something")
  (GET "/categories" [] categories/index)
  (GET "/categories/:id" [] "category by id")
  (GET "/movement/:id" [] "movement by id"))

(def app (wrap-defaults routes site-defaults))

(defn -main
  "Start the server on $PRS_PORT or the default port 3000"
  []

  (let [port-number (Integer. (or (System/getenv "PRS_PORT") "3000"))]
    (ring/run-jetty app {:port port-number :join? false})))


