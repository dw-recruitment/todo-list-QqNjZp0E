(ns clojure-todo-list.db
  (:require [clojure.java.jdbc :as sql])
  (:require [clojure.string :as str]))

(def db-spec {:classname "org.sqlite.JDBC"
              :subprotocol "sqlite"
              :subname "todo.db"})

(defn get-all-lists
  []
  (let [results
    (sql/query db-spec "select id, name from list")]
    results))

(defn get-one-list
  [id]
  (let [results
    (sql/query db-spec ["select todo.id, todo.item, todo.done, list.name from todo join list on todo.list = list.id where todo.list = ?" (first id)])
    ]
    results))

(defn get-all-todos
  []
  (let [results
    (sql/query db-spec "select todo.id, todo.item, todo.done, list.name from todo join list on todo.list = list.id")]
    results))

(defn add-todo-item-to-db
  [item]
  (let [results
    (sql/insert! db-spec :todo {:item item :done 0})]
  (get-all-todos)))

(defn add-todo-item-to-list
  [params]
  (let [results
    (sql/insert! db-spec :todo {:item (:item params) :done 0 :list (:id params)})]
  )
)

(defn set-item-as-done
  [id]
  (case (str (second id))
    "" (sql/execute! db-spec ["update todo set done = 0 where id = ?" (first id)])
    "on" (sql/execute! db-spec ["update todo set done = 1 where id = ?" (first id)])
    ))

(defn delete-item
    [id]
    (sql/execute! db-spec ["delete from todo where id = ?" (first id)]))

(defn add-new-list
  [params]
  (println params)
  (sql/insert! db-spec :list {:name (:name params)})
  )

(defn delete-list
    [id]
    (sql/execute! db-spec ["delete from list where id = ?" (first id)])
    (sql/execute! db-spec ["delete from todo where list = ?" (first id)]))
