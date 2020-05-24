(defproject prs "0.1.0-SNAPSHOT"
  :description "Small mobile only web app to track PRs"
  :url "https://www.github.com/robmerrell/prs"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [lein-cljfmt "0.6.7"]
                 [mount "0.1.16"]
                 [aero "1.1.6"]

                 ;; http
                 [compojure "1.6.1"]
                 [ring/ring-core "1.8.1"]
                 [ring/ring-jetty-adapter "1.8.1"]
                 [ring/ring-devel "1.8.1"]
                 [ring/ring-defaults "0.3.2"]

                 ;; html
                 [hiccup "1.0.5"]

                 ;; database
                 [com.layerware/hugsql "0.5.1"]
                 [org.xerial/sqlite-jdbc "3.30.1"]]
  :main ^:skip-aot prs.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
