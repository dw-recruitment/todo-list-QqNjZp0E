(ns clojure-todo-list.db
  (:require [clojure.java.jdbc :as sql])
  (:require [clojure.string :as str]))

(def db-spec {:classname "org.sqlite.JDBC"
              :subprotocol "sqlite"
              :subname "todo.db"})

(defn get-all-todos
  []
  (let [results
    (sql/query db-spec "select id, item, done from todo")]
    results))

(defn add-todo-item-to-db
  [item]
  (let [results
    (sql/insert! db-spec :todo {:item item :done 0})]
  (get-all-todos)))

(defn set-item-as-done
  [id]
    (sql/execute! db-spec ["update todo set done = 1 where id = ?" (first id)]))


    ; (sql/execute! db-spec ["UPDATE todo SET done = ? where id = ?" 1 (:done form_data)])]

    ; (sql/update! db-spec :todo {:done 1} [:id (:id form_data)])]))
    ; (sql/update! db-spec :todo {:done 1} ["id = ?" 1]))
