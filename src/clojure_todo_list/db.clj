(ns clojure-todo-list.db
  (:require [clojure.java.jdbc :as sql]))

(def db-spec {:classname "org.sqlite.JDBC"
              :subprotocol "sqlite"
              :subname "todo.db"})

(defn get-all-todos
  []
  (let [results
    (sql/query db-spec "select item, done from todo")]
    results))

; (def make-new-todo
;   [item]
;   (let [results (sql/insert! db :todo {:item item :done 0})]))
