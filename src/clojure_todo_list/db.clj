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

(defn add-todo-item-to-db
  [item]
  (let [results
    (sql/insert! db-spec :todo {:item item :done 0})]
  (get-all-todos)))

(defn set-item-as-done
  [item]
  (let [results
    (sql/execute! db-spec ["UPDATE todo SET done = ? where id = ?" 1 8])]))



    ; (sql/update! db-spec :todo {:done 1} [:id (:id item)])]))
