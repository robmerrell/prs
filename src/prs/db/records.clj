(ns prs.db.records
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/records.sql")
