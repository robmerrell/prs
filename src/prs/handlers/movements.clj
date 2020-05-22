(ns prs.handlers.movements
  (:require [prs.layout :refer [render]]
            [prs.views.movements :as views]))

(defn show
  [req]
  (render req views/show {:title "Movement"}))
