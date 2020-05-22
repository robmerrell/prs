(ns prs.core
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.util.response :as response]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [prs.handlers.categories :as categories]
            [prs.handlers.movements :as movements])
  (:gen-class))

(defroutes routes
  (GET "/" [] (response/redirect "/categories"))
  (GET "/categories" [] categories/index)
  (GET "/categories/:id" [] "category by id")
  (GET "/movements/:id" [] movements/show)
  (route/resources "/"))

(def app (wrap-defaults routes site-defaults))
(def app-dev (wrap-reload(wrap-defaults #'routes site-defaults)))

(defn- port
  "get the port from the envionrment variable PRS_PORT or default to 3000"
  []
  (Integer. (or (System/getenv "PRS_PORT") "3000")))

(defn -main
  "Start the server on $PRS_PORT or the default port 3000"
  [& args]
  (if (= (first args) "dev")
    (do
      (println "starting dev mode")
      (ring/run-jetty app-dev {:port (port) :join? false}))
    (do
      (println "starting prod mode")
      (ring/run-jetty app {:port (port)}))))
