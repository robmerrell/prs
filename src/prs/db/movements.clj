(ns prs.db.movements
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/movements.sql")
