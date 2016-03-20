(ns clojure-todo-list.core
  (:gen-class)
  (:require [clojure.java.jdbc :as sql]
            [clojure.string :as str]
            [hiccup.page :as hic-p]
            [clojure-todo-list.db :as db]))

(def db {:classname "org.sqlite.JDBC"
         :subprotocol "sqlite"
         :subname "todo.db"})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn home [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (let [all-todos (db/get-all-todos)]
            (hic-p/html5
              [:h1 "Super cool awesome headline"]
              [:table
                [:tr [:th "item"] [:th "done"]]
                (for [todo all-todos]
                  [:tr [:td (:item todo)[:td (:done todo)]]])]
              [:h2 "Add Another TODO"]
              [:form {:action "/" :method "POST"}
                [:p "new item" [:input {:type "text" :name "item"}]]
                [:p [:input {:type "submit" :value "add new item"}]]]))})

(defn about [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<h1>About this project</h1>
          <p>This is a basic webapp to:</p>
          <ul>
            <li>help me learn ~*clojure*~</li>
            <li>keep track of #TODO items</li>
            <li>reinforce that learning new languages is fun!</li>
          </ul>"})

(defn error [request]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body "<h1>404 Not Found</h1"})

(defn handler [request]
  (case (request :uri)
    "/" (home request)
    "/about" (about request)
    (error request)))
