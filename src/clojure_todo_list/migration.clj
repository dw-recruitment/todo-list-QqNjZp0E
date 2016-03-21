; not sure how to make it run this, used as template to run creation commands in REPL
(ns clojure-todo-list.migration
    (:require [clojure.java.jdbc :as sql]))

(def db {
      :classname "org.sqlite.JDBC"
      :subprotocol "sqlite"
      :subname "db/todo-list.db"})

(defn create-todo []
        (sql/create-table-ddl :todo
          [:id :serial "PRIMARY KEY"]
          [:item :varchar "NOT NULL"]
          [:done :integer "NOT NULL"])
)

(defn -main []
      (print "Migrating database ... ") (flush)
      (create-todo)
      (println "Done ...")
)


; things that work
; (let [cs (sql/create-table-ddl :todo1 [:id :primary :key :autoincrement] [:item :varchar "NOT NULL"] [:done :integer "NOT NULL"])] (sql/execute! db [cs]))
; (sql/insert! db :todo {:item "do laundry" :done 0})
; (sql/insert! db :todo {:item "rearrange furniture" :done 0})

; (require '[clojure.java.jdbc :as sql])
; (def db {:classname "org.sqlite.JDBC" :subprotocol "sqlite" :subname "todo.db"})
; (sql/query db "select item, done from todo")
