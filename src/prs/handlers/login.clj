(ns prs.handlers.login
  (:require [prs.layout :refer [render]]
            [prs.views.login :as views]
            [prs.response :as res]
            [prs.db.core :as db]
            [prs.db.users :as users-db]
            [buddy.hashers :as hashers]))

(defn index
  "Show the login form"
  [req]
  (render req views/index {:title "Login"}))

(defn process-login
  "Processes the given login information and stores the user id in the session.
  On successful login the user is redirected to the categories page and the login
  form if unsuccessful."
  [req]
  (let [email (get-in req [:form-params "email"])
        password (get-in req [:form-params "password"])
        user (users-db/get-by-email db/conn {:email email})]
    (if (hashers/check password (:password user))
      (res/redirect "/categories" {:session {:identity (:id user)}})
      (res/redirect "/login" {:flash {:error "Invalid email or password"}}))))
