(ns prs.db.categories
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/categories.sql")
