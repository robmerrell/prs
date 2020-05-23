(ns prs.server
  (:require [mount.core :as mnt]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.util.response :as response]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [prs.config :as config]
            [prs.handlers.categories :as categories]
            [prs.handlers.movements :as movements]))

(defroutes routes
  (GET "/" [] (response/redirect "/categories"))
  (GET "/categories" [] categories/index)
  (GET "/categories/:id" [] "category by id")
  (GET "/movements/:id" [] movements/show)
  (route/resources "/"))

(def app (wrap-defaults routes site-defaults))
(def app-dev (wrap-reload(wrap-defaults #'routes site-defaults)))

(defn start-server []
  (if (= :dev (:profile mnt/args))
    (ring/run-jetty app-dev {:port (config/webserver-port) :join? false})
    (ring/run-jetty app {:port (config/webserver-port)})))

(defn stop-server [server]
  (.stop server))

(mnt/defstate server
  :start (start-server)
  :stop (stop-server server))
