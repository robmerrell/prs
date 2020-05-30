(ns prs.server
  (:require [mount.core :as mnt]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.util.response :as response]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [buddy.auth.backends :as backends]
            [taoensso.timbre :as log]
            [prs.config :as config]
            [prs.db.core :as db]
            [prs.db.users :as users-db]
            [prs.handlers.categories :as categories]
            [prs.handlers.movements :as movements]
            [prs.handlers.login :as login]))

(defroutes routes
  (GET "/" [] categories/index)
  (GET "/login" [] login/index)
  (POST "/login" [] login/process-login)
  (GET "/categories" [] categories/index)
  (GET "/categories/:id" [] categories/show)
  (GET "/movements/:id" [] movements/show)
  (route/resources "/"))

;; authentication
(defn unauthorized-handler
  "Handles unauthorized requests by redirecting to the login page"
  [_req _metadata]
  (response/redirect "/login"))

(def backend (backends/session {:unauthorized-handler unauthorized-handler}))

(defn wrap-load-user
  "Middleware that loads the authenticated user into the session"
  [handler]
  (fn [req]
    (if-let [id (get-in req [:session :identity])]
      (handler
       (assoc req :user (users-db/get-by-id db/conn {:id id})))
      (handler req))))

;; app definitions
(def app (wrap-defaults routes site-defaults))
(def app-dev
  (-> #'routes
      (wrap-load-user)
      (wrap-authorization backend)
      (wrap-authentication backend)
      (wrap-defaults site-defaults)
      (wrap-reload)))


;; mount
(defn start-server []
  (if (= :dev (:profile (mnt/args)))
    (do
      (log/info "Starting in dev mode")
      (ring/run-jetty app-dev {:port (config/webserver-port) :join? false}))
    (do
      (log/info (str "Starting with profile: " (:profile (mnt/args))))
      (ring/run-jetty app {:port (config/webserver-port)}))))

(defn stop-server [server]
  (.stop server))

(mnt/defstate ^{:on-reload :noop} server
  :start (start-server)
  :stop (stop-server server))
